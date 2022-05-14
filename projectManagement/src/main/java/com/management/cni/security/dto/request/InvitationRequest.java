package com.management.cni.security.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationRequest {

  private long projectId;

  private long memberId;

}
