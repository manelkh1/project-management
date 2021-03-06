package com.management.cni.security.dto.response;

import com.management.cni.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectResponse {

  private long id;

  private String title;

  private String description;

  private Date startDate;

  private Date endDate;

  private Date creationDate;

  private Status status;

  //private ManagerResponse manager;

  //private List<CommentResponse> comments;

  //private List<AttachmentResponse> Attachments;

  //private List<InvitationResponse> invitations;

}
