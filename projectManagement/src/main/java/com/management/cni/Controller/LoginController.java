package com.management.cni.Controller;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.management.cni.Entity.Session;
import com.management.cni.Entity.User;
import com.management.cni.Object.AuthenticationResponse;
import com.management.cni.Repository.UserRepository;
import com.management.cni.Service.SessionService;
import com.management.cni.security.JwtUtil;
import com.management.cni.security.MyUserDetailsService;
// allows all origins, all headers
@CrossOrigin(origins = "*", allowedHeaders = "*")
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


	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
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

		return ResponseEntity.ok(new AuthenticationResponse(user, jwt));

	}
	
	@Transactional
	@GetMapping("/activation")
	public RedirectView activation(@RequestParam("token") String token) {
		Session session = sessionService.findByToken(token);
		
		if(session == null) {
			return new RedirectView("http://localhost:4200/error-404");
		}else {
			User user = session.getUser();
			if(!user.isEnabled()) {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				if(session.getLastConnection().before(timestamp)) {
					return new RedirectView("http://localhost:4200/expired-token");
				}else {
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
