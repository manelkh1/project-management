package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.AdminRequest;
import com.management.cni.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/admins")
public class AdminController {

  @Autowired
  private AdminService adminService;

  @GetMapping("")
  public ResponseEntity<ApiResponse> getAllAdmins() {
    ApiResponse apiResponse = adminService.getAllAdmins();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getAdminById(@PathVariable("id") long id) {
    ApiResponse apiResponse = adminService.getAdminById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

/*
  @GetMapping("/admins")
  public ResponseEntity<ApiResponse> getAllAdmins() {
    ApiResponse apiResponse = adminService.getAllAdmins();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }
*/

  @PostMapping("")
  public ResponseEntity<ApiResponse> createAdmin(@RequestBody AdminRequest adminRequest) {
    ApiResponse apiResponse = adminService.createAdmin(adminRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> updateAdmin(@PathVariable("id") long id, @RequestBody AdminRequest adminRequest) {
    ApiResponse apiResponse = adminService.updateAdmin(id, adminRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteAdminById(@PathVariable("id") long id) {
    ApiResponse apiResponse = adminService.deleteAdminById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

}
