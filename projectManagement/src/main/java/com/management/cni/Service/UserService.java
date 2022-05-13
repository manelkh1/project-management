package com.management.cni.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.cni.Entity.Invitation;
import com.management.cni.Entity.Project;
import com.management.cni.Entity.User;
import com.management.cni.Repository.InviationRepository;
import com.management.cni.Repository.UserRepository;

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
	public Optional<User> findUserByEmail(String email){
		return userRepository.findUserByEmail(email);
	}

	
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	// Add User
	@Transactional
	public User addUser(User user) {
		//disable user before activation
		user.setStatus(false);//cannot be authenticated
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		
		Optional<User> savedUser =Optional.of(saveUser(user));
		
		savedUser.ifPresent(u->{
			try {
				String token = UUID.randomUUID().toString();//generate token randomly
				sessionService.saveSession(savedUser.get(), token);//put a token and a saveduser in a session
				emailService.sendHtmlMail(u); // send an email that contain an api with token and user				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});		
		return savedUser.get();
	}
	
	@Transactional
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@Transactional
	public List<User> findMembers(String keyword){
		return userRepository.findAllMembers(keyword);
	}
	
	// Get Current User	
	@Transactional
	public User currentUser() {
		SecurityContext principal = SecurityContextHolder.getContext();
		Authentication authentication = principal.getAuthentication();
		User user = null ;
		if (authentication != null)
            if (authentication.getPrincipal() instanceof User)
            	user = ((User) authentication.getPrincipal());
            else if (authentication.getPrincipal() instanceof String)
            	user = (User) authentication.getPrincipal();
		return user;
	}
	
	
	@Transactional
	public Optional<User> getUserById(Long userId){
		return userRepository.findById(userId);
	}


	

}
	

