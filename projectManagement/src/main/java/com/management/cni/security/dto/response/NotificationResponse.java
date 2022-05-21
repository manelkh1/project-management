package com.management.cni.security.dto.response;

import com.management.cni.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponse {

  private Long id;

  private String time;

  private Status status;

  private UserResponse user;

  ///private NotificationMessageResponse notificationMessage;
}
