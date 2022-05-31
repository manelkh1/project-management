package com.management.cni.security.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.cni.entity.Manager;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentResponse {

  private long Id;

  private String message;

  private Timestamp time;

  private String sender;

  @JsonIgnore
  private ManagerResponse manager;

  @JsonIgnore
  private MemberResponse member;

  private ProjectResponse project;

}
