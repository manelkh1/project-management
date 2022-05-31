package com.management.cni.controller;

import com.management.cni.entity.Session;
import com.management.cni.entity.User;
import com.management.cni.repository.UserRepository;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.JwtUtil;
import com.management.cni.security.MyUserDetailsService;
import com.management.cni.security.dto.request.LoginRequest;
import com.management.cni.security.dto.response.LoginResponse;
import com.management.cni.security.dto.response.UserResponse;
import com.management.cni.security.mapper.UserMapper;
import com.management.cni.service.SessionService;
import com.management.cni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.time.LocalDateTime;

// allows all origins, all headers
@CrossOrigin
@RestController
public class LoginController {

  @Autowired
  //Attempts to authenticate the passed {@link Authentication} object
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtTokenUtil;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MyUserDetailsService userDetailsService;
  @Autowired
  private SessionService sessionService;
  @Autowired
  private  UserService userService;

/*  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {

    try {
      authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

    final UserDetails userDetails = userDetailsService
      .loadUserByUsername(authenticationRequest.getUsername());
//find the user by his email related to the request
    User user = userRepository.findByEmail(authenticationRequest.getUsername());
//generate a token to the user
    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(null, jwt));
  }*/


  @PostMapping("/login")
  public ResponseEntity<ApiResponse> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {

    final String username = loginRequest.getUsername();
    final String password = loginRequest.getPassword();

    final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    final User user = userService.findUserByEmail(username);

  //  final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
    final String token = jwtTokenUtil.generateToken(user);
    UserResponse response = UserMapper.INSTANCE.convertToUserResponse(user);
    LoginResponse loginResponse = new LoginResponse(token, response);
   // log.info(" {} has successfully logged in!", user.getUsername());
    ApiResponse apiResponse =new ApiResponse(loginResponse, "USER LOGED IN ", HttpStatus.OK, LocalDateTime.now());
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @Transactional
  @GetMapping("/activation")
  public RedirectView activation(@RequestParam("token") String token) {
    Session session = sessionService.findByToken(token);

    if (session == null) {
      return new RedirectView("http://localhost:4200/error-404");
    } else {
      User user = session.getUser();
      if (!user.isEnabled()) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (session.getLastConnection().before(timestamp)) {
          return new RedirectView("http://localhost:4200/expired-token");
        } else {
          user.setStatus(true);
          userRepository.save(user);
					return new RedirectView("http://localhost:4200/successefully-activated");
				}
			}else {
				return new RedirectView("http://localhost:4200/already-activated");
			}
		}
	}

}
