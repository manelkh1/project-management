package com.management.cni.service;

import com.management.cni.Entity.Manager;
import com.management.cni.Entity.User;
import com.management.cni.Repository.ManagerRepository;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.ManagerRequest;
import com.management.cni.security.dto.response.ManagerResponse;
import com.management.cni.security.mapper.ManagerMapper;
import com.management.cni.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ManagerService {

  @Autowired
  ManagerRepository managerRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private SessionService sessionService;

  public ApiResponse getAllManagers() {
    User user = userService.getConnectedAdmin();
    List<ManagerResponse> managerResponses = new ArrayList<>();
    try {

      if (user != null) {
        List<Manager> managers = managerRepository.findAll();
        if (!managers.isEmpty()) {
          for (Manager manager : managers) {
            ManagerResponse managerResponse = ManagerMapper.INSTANCE.convertToManagerResponse(manager);
            managerResponses.add(managerResponse);
          }
        }
        return new ApiResponse(managerResponses, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse getManagerById(Long id) {
    User user = userService.getConnectedAdmin();
    try {
      if (user.getAdmin() != null || user.getMember() != null || user.getManager() != null) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
          ManagerResponse managerResponse = ManagerMapper.INSTANCE.convertToManagerResponse(manager.get());
          return new ApiResponse(managerResponse, null, HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "MANAGER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse createManager(ManagerRequest managerRequest) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Manager manager = ManagerMapper.INSTANCE.convertToManager(managerRequest);
      ///convert userRequest to USER PERSISTENCE SO YOU CAN STORE IT IN DATABASE

      String password = passwordEncoder.encode(managerRequest.getUser().getPassword());
        manager.getUser().setPassword(password);
      manager.getUser().setStatus(false);
      String token = UUID.randomUUID().toString();//generate token randomly
      sessionService.saveSession(manager.getUser(), token);//put a token and a saveduser in a session
     // emailService.sendHtmlMail(manager.getUser());
        managerRepository.save(manager);
        return new ApiResponse(null, "MANAGER CREATED", HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse deleteManagerById(Long id) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
          managerRepository.deleteById(id);
          return new ApiResponse(null, "MANAGER DELETED", HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "MANAGER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse updateManager(Long id, ManagerRequest managerRequest) {
    User user = userService.getConnectedManager();
    try {
      if (user != null) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
          User newUser = manager.get().getUser();

          newUser.setFirstName(managerRequest.getUser().getFirstName());
          newUser.setLastName(managerRequest.getUser().getLastName());
          newUser.setEmail(managerRequest.getUser().getEmail());
          user.getManager().setUser(newUser);
          managerRepository.save(user.getManager());
          return new ApiResponse(null, "MANAGER UPDATED", HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "MANAGER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

}
