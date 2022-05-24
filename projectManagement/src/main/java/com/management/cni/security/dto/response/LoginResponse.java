package com.management.cni.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
public class LoginResponse {

    private String token;

    private UserResponse user;
  public LoginResponse(String token, UserResponse user) {
    this.token = token;
    this.user = user;
  }

}
