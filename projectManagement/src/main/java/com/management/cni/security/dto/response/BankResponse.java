package com.management.cni.security.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankResponse {

  private Long id;

  private String bankName;

  private UserResponse user;

}
