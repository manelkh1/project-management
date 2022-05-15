package com.management.cni.security.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankRequest {

  private String bankName;

  private UserRequest userRequest;

}
