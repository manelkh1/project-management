package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.ProjectRequest;
import com.management.cni.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

  @Autowired
  private ProjectService projectService;


  //get admin projects
  @GetMapping("/projectsByAdmin")
  public ResponseEntity<ApiResponse> getAllProjectsByAdmin() {
    ApiResponse apiResponse = projectService.getAllProjectsByAdmin();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  //get manager projects
  @GetMapping("/projectsByManager")
  public ResponseEntity<ApiResponse> getAllProjectsByManager() {
    ApiResponse apiResponse = projectService.getAllProjectsByManager();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  //get member projects
  @GetMapping("/projectsByMember")
  public ResponseEntity<ApiResponse> getAllProjectsByMember() {
    ApiResponse apiResponse = projectService.getAllProjectsByMember();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  //get bank projects
  @GetMapping("/projectsByBank")
  public ResponseEntity<ApiResponse> getAllProjectsByBank() {
    ApiResponse apiResponse = projectService.getAllProjectsByBank();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  // get project details
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getProjectById(@PathVariable("id") long id) {
    ApiResponse apiResponse = projectService.getProjectById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  //add project
  @PostMapping("")
  public ResponseEntity<ApiResponse> createProject(@RequestBody ProjectRequest projectRequest) {
    ApiResponse apiResponse = projectService.createProject(projectRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("/{id}/acceptProject")
  public ResponseEntity<ApiResponse> acceptProject(@PathVariable("id") long id) {
    ApiResponse apiResponse = projectService.acceptProject(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("/{id}/sendProject")
  public ResponseEntity<ApiResponse> sendProject(@PathVariable("id") long id) {
    ApiResponse apiResponse = projectService.sendProject(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }


  //update project
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> updateProject(@PathVariable("id") long id, @RequestBody ProjectRequest projectRequest) {
    ApiResponse apiResponse = projectService.updateProject(id, projectRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  //delete project
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteProjectById(@PathVariable("id") long id) {
    ApiResponse apiResponse = projectService.deleteProjectById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

}
