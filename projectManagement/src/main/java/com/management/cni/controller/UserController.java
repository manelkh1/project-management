package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.UserRequest;
import com.management.cni.service.ProjectService;
import com.management.cni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ProjectService projectService;

  @PostMapping("/addUser")
  public ResponseEntity<ApiResponse> addUser(@RequestBody UserRequest userRequest) {
    ApiResponse apiResponse = userService.addUser(userRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }
/*
	@CrossOrigin(origins = "*",allowedHeaders = "*")
	   @GetMapping("/allUsers")
	   public List<User> getAllUsers(){
	   return userService.getAllUsers();
	   }

	*/
/*	@GetMapping("/allRole")
		public List<Role> getAllRole(){
			return roleRepository.findAll();
		}
*//*

		@GetMapping("findMember/{keyword}")
		public List<User> findMembers(@PathVariable(value = "keyword") String keyword){
			return userService.findMembers(keyword);
		}

		@GetMapping("/userById/{userId}")
		public Optional<User> getUserById(@PathVariable(value = "userId") Long userId){
			return userService.getUserById(userId);
		}

		@GetMapping("/projects/{userId}")
		public List<Project> getProjectByUserId(@PathVariable(value = "userId") Long userId){

			return projectService.getProjectByUserId(userId) ;
		}
*/
}


