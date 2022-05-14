package com.management.cni.security.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentRequest {

  private String name;

  private byte[] fileContent;

}
