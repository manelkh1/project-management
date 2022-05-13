package com.management.cni.Object;

import java.io.Serializable;

import com.management.cni.Entity.User;

public class AuthenticationResponse implements Serializable{

	private User user;
	private String jwt;
	
	public AuthenticationResponse(User user, String jwt) {
		super();
		this.user = user;
		this.jwt = jwt;
	}
	
	public User getUser() {
		return user;
	}

	public String getJwt() {
		return jwt;
	}

	
	
}
