
package com.management.cni.security.dto.response;

import com.management.cni.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

  private Long id;

  private String firstName;

  private String lastName;

  private byte[] photo;

  private String post;

  private String email;

  private String password;

  private Boolean status;

  private String city;

  private String country;

  private String codePostal;

//  private Session session;

  private UserRole userRole;

}
