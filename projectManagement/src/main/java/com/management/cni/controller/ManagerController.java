package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.ManagerRequest;
import com.management.cni.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/managers")
public class ManagerController {

  @Autowired
  private ManagerService managerService;

  @GetMapping("")
  public ResponseEntity<ApiResponse> getAllManagers() {
    ApiResponse apiResponse = managerService.getAllManagers();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getManagerById(@PathVariable("id") long id) {
    ApiResponse apiResponse = managerService.getManagerById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

/*
  @GetMapping("/managers")
  public ResponseEntity<ApiResponse> getAllManagers() {
    ApiResponse apiResponse = managerService.getAllManagers();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }
*/

  @PostMapping("")
  public ResponseEntity<ApiResponse> createManager(@RequestBody ManagerRequest managerRequest) {
    ApiResponse apiResponse = managerService.createManager(managerRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> updateManager(@PathVariable("id") long id, @RequestBody ManagerRequest managerRequest) {
    ApiResponse apiResponse = managerService.updateManager(id, managerRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteManagerById(@PathVariable("id") long id) {
    ApiResponse apiResponse = managerService.deleteManagerById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

}
