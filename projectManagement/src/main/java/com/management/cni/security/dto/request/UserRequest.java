
package com.management.cni.security.dto.request;

import com.management.cni.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

  private String firstName;

  private String lastName;

  private byte[] photo;

  private String post;

  private String email;

  private String password;

  private String city;

  private String country;

  private String codePostal;

  private UserRole userRole;

}
