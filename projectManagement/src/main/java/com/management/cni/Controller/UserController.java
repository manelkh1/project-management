package com.management.cni.Controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.management.cni.Entity.Invitation;
import com.management.cni.Entity.Project;
import com.management.cni.Entity.Role;
import com.management.cni.Entity.Session;
import com.management.cni.Entity.User;
import com.management.cni.Object.MessageResponse;
import com.management.cni.Repository.RoleRepository;
import com.management.cni.Service.ProjectService;
import com.management.cni.Service.SessionService;
import com.management.cni.Service.UserService;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ProjectService projectService;

	@PostMapping("/add/{roleId}")
	public ResponseEntity<?> addUser(@RequestBody User user, @PathVariable Long roleId)
	{
		//if the email is already exists
		if(userService.findUserByEmail(user.getEmail()).isPresent()) {
			return ResponseEntity.ok(new MessageResponse("Email already exists", "Error"));
		}		
		//if the email doesn't exist yet
		Role role = roleRepository.getOne(roleId);
		user.setRole(role);
		userService.addUser(user);		
		return ResponseEntity.ok(new MessageResponse("Success! Please activate your email", "Success"));
	}
	
	@CrossOrigin(origins = "*",allowedHeaders = "*")
	   @GetMapping("/allUsers")
	   public List<User> getAllUsers(){
	   return userService.getAllUsers();
	   } 
	
		@GetMapping("/allRole")
		public List<Role> getAllRole(){
			return roleRepository.findAll();
		}
	
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
		

	
	}


