package com.management.cni.security.dto.request;

import com.management.cni.Entity.Status;
import lombok.Getter;

import java.util.Date;

@Getter
public class ProjectRequest {

  private String title;

  private String description;

  private Date startDate;

  private Date endDate;

  private Status status;

}
