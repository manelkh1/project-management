package com.management.cni.security.dto.response;

import com.management.cni.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class InvitationResponse {

  private long id;

  private Timestamp date;

  private Status status;

  private ManagerResponse manager;

  private MemberResponse member;

  private ProjectResponse project;

}
