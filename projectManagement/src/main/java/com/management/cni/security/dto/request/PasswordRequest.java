package com.management.cni.security.dto.request;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PasswordRequest {

  private String oldPassword;

  private String newPassword;

  private String confirmedNewPassword;
}
