package com.management.cni.security.dto.response;

import com.management.cni.Entity.Manager;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentResponse {

  private long Id;

  private String message;

  private Timestamp time;

  private Manager manager;

  private MemberResponse member;

  private ProjectResponse project;

}
