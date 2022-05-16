package com.management.cni.service;

import com.management.cni.Entity.Admin;
import com.management.cni.Entity.User;
import com.management.cni.Repository.AdminRepository;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.AdminRequest;
import com.management.cni.security.dto.response.AdminResponse;
import com.management.cni.security.mapper.AdminMapper;
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
public class AdminService {

  @Autowired
  AdminRepository adminRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private SessionService sessionService;

  public ApiResponse getAllAdmins() {
    User user = userService.getConnectedAdmin();
    List<AdminResponse> adminResponses = new ArrayList<>();
    try {

      if (user != null) {
        List<Admin> admins = adminRepository.findAll();
        if (!admins.isEmpty()) {
          for (Admin admin : admins) {
            AdminResponse adminResponse = AdminMapper.INSTANCE.convertToAdminResponse(admin);
            adminResponses.add(adminResponse);
          }
        }
        return new ApiResponse(adminResponses, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse getAdminById(Long id) {
    User user = userService.getConnectedAdmin();
    try {
      if (user.getAdmin() != null || user.getMember() != null || user.getAdmin() != null) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
          AdminResponse adminResponse = AdminMapper.INSTANCE.convertToAdminResponse(admin.get());
          return new ApiResponse(adminResponse, null, HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "ADMIN DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse createAdmin(AdminRequest adminRequest) {
  //  User user = userService.getConnectedAdmin();
    try {
    //  if (user != null) {
        Admin admin = AdminMapper.INSTANCE.convertToAdmin(adminRequest);
      ///convert userRequest to USER PERSISTENCE SO YOU CAN STORE IT IN DATABASE

      String password = passwordEncoder.encode(adminRequest.getUser().getPassword());
        admin.getUser().setPassword(password);
      admin.getUser().setStatus(false);
      String token = UUID.randomUUID().toString();//generate token randomly
      sessionService.saveSession(admin.getUser(), token);//put a token and a saveduser in a session
     // emailService.sendHtmlMail(admin.getUser());
        adminRepository.save(admin);
        return new ApiResponse(null, "ADMIN CREATED", HttpStatus.OK, LocalDateTime.now());
     /* } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }*/
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse deleteAdminById(Long id) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
          adminRepository.deleteById(id);
          return new ApiResponse(null, "ADMIN DELETED", HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "ADMIN DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse updateAdmin(Long id, AdminRequest adminRequest) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
          User newUser = admin.get().getUser();

          newUser.setFirstName(adminRequest.getUser().getFirstName());
          newUser.setLastName(adminRequest.getUser().getLastName());
          newUser.setEmail(adminRequest.getUser().getEmail());
          user.getAdmin().setUser(newUser);
          adminRepository.save(user.getAdmin());
          return new ApiResponse(null, "ADMIN UPDATED", HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "ADMIN DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

}
