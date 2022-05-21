package com.management.cni.Object;

import com.management.cni.entity.User;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

  private final User user;
  private final String jwt;

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
