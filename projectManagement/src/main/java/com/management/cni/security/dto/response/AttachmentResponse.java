package com.management.cni.security.dto.response;

import com.management.cni.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AttachmentResponse {

  private long id;

  private String name;

  private Timestamp creationDate;

  private byte[] fileContent;

  private ProjectResponse project;

  private MemberResponse member;

  private ManagerResponse manager;

  private Status status;

  private String fileName;

  private String fileDownloadUri;

  private String fileType;

  private long size;

}
