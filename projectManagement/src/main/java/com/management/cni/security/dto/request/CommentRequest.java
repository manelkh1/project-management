package com.management.cni.security.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

  private String message;

  private long idProject;

}
