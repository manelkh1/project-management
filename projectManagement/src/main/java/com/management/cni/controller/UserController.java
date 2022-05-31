package com.management.cni.controller;

import com.management.cni.entity.User;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.PasswordRequest;
import com.management.cni.security.dto.request.UserRequest;
import com.management.cni.service.ProjectService;
import com.management.cni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;


  @PostMapping("/addUser")
  public ResponseEntity<ApiResponse> addUser(@RequestBody UserRequest userRequest) {
    ApiResponse apiResponse = userService.addUser(userRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("")
  public ResponseEntity<ApiResponse> getAllUsers() {
    ApiResponse apiResponse = userService.getAllUsers();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("/changePassword")
  public ResponseEntity<ApiResponse> changePassword(@RequestBody PasswordRequest passwordRequest) {
    ApiResponse apiResponse = userService.changePassword(passwordRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }
}


