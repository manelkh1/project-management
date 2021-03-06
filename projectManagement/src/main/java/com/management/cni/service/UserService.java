package com.management.cni.service;

import com.management.cni.entity.User;
import com.management.cni.entity.UserRole;
import com.management.cni.repository.UserRepository;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.PasswordRequest;
import com.management.cni.security.dto.request.UserRequest;
import com.management.cni.security.dto.response.UserResponse;
import com.management.cni.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EmailService emailService;
  @Autowired
  private SessionService sessionService;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Transactional
  public User findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  public User findByUsername(String username) {

    return userRepository.findByUsername(username);
  }

  public UserResponse findAuthenticatedUserByUsername(String username) {

    final User user = findByUsername(username);

    return UserMapper.INSTANCE.convertToUserResponse(user);
  }

  @Transactional
  public User saveUser(User user) {
    return userRepository.save(user);
  }


  public User getConnectedAdmin() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findUserByEmail(((UserDetails) principal).getUsername());
    if (user != null) {
      /// tester si le role de user est ADMIN
      if (user.getAdmin() != null && user.getUserRole().equals(UserRole.ADMIN)) {
        return user;
      }
    }
    return null;
  }

  public User getConnectedManager() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findUserByEmail(((UserDetails) principal).getUsername());
    if (user != null) {
      if (user.getManager() != null && user.getUserRole().equals(UserRole.MANAGER)) {
        return user;
      }
    }
    return null;
  }

  public User getConnectedMember() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findUserByEmail(((UserDetails) principal).getUsername());
    if (user != null) {
      if (user.getMember() != null && user.getUserRole().equals(UserRole.MEMBER)) {
        return user;
      }
    }
    return null;
  }

  public User getConnectedBank() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findUserByEmail(((UserDetails) principal).getUsername());
    if (user != null) {
      if (user.getBank() != null && user.getUserRole().equals(UserRole.BANK)) {
        return user;
      }
    }
    return null;
  }

  public User getConnectedUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findUserByEmail(((UserDetails) principal).getUsername());
    return user;
  }


  // Add User
  @Transactional
  public ApiResponse addUser(UserRequest userRequest) {
    //if the email already exists
    User connectedAdmin = getConnectedAdmin();
    /// si elle retourne ADMIN et not null
    if (connectedAdmin != null) {

      try {
        if (findUserByEmail(userRequest.getEmail()) != null) {
          //return ResponseEntity.ok(new MessageResponse("Email already exists", "Error"));
          return new ApiResponse(null, "Email already exists", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }

        ///convert userRequest to USER PERSISTENCE entity SO YOU CAN STORE IT IN DATABASE
        User user = UserMapper.INSTANCE.convertToUser(userRequest);
        String password = passwordEncoder.encode(userRequest.getPassword());
        user.setPassword(password);
        user.setStatus(false);
        String token = UUID.randomUUID().toString();//generate token randomly
        sessionService.saveSession(user, token);//put a token and a saveduser in a session
        emailService.sendHtmlMail(user);
        userRepository.save(user);
        return new ApiResponse(null, "USER IS CREATED", HttpStatus.OK, LocalDateTime.now());
      } catch (Exception e) {
        return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
      }
    } else {
      return new ApiResponse(null, "THIS USER IS NOT ADMIN : " + connectedAdmin.getUsername(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());
    }
  }


  public ApiResponse changePassword(PasswordRequest passwordRequest) {
    //if the email is already exists
    User user = getConnectedUser();
    if (user != null) {

      try {
        if (passwordEncoder.matches(passwordRequest.getOldPassword(),user.getPassword())) {
          if (passwordRequest.getNewPassword().equals(passwordRequest.getConfirmedNewPassword())){
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            userRepository.save(user);
            return new ApiResponse(null, "password is changed ", HttpStatus.OK, LocalDateTime.now());

          } else  {
            return new ApiResponse(null, "password doesn't matches ", HttpStatus.BAD_REQUEST, LocalDateTime.now());
          }
        } else {
          return new ApiResponse(null, "wrong password", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } catch (Exception e) {
        return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
      }
    } else {
      return new ApiResponse(null, "THIS USER IS NOT ADMIN : ", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
    }
  }

  @Transactional
  public ApiResponse getAllUsers() {
    User connectedAdmin = getConnectedAdmin();
    List<UserResponse> userResponses = new ArrayList<>();
    try {

      if (connectedAdmin != null) {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
          for (User user : users) {
            UserResponse userResponse = UserMapper.INSTANCE.convertToUserResponse(user);
            userResponses.add(userResponse);
          }
        }
        return new ApiResponse(userResponses, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  @Transactional
  public List<User> findMembers(String keyword) {
    return userRepository.findAllMembers(keyword);
  }

  // Get Current User
  @Transactional
  public User currentUser() {
    SecurityContext principal = SecurityContextHolder.getContext();
    Authentication authentication = principal.getAuthentication();
    User user = null;
    if (authentication != null)
      if (authentication.getPrincipal() instanceof User) user = ((User) authentication.getPrincipal());
      else if (authentication.getPrincipal() instanceof String) user = (User) authentication.getPrincipal();
    return user;
  }

  @Transactional
  public Optional<User> getUserById(Long userId) {
    return userRepository.findById(userId);
  }



}


