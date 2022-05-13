package com.management.cni.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_Notification_Message")
public class NotificationMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "message")
	private String message;
	
	@JsonIgnore
	@OneToMany(mappedBy = "notificationMessage")
	private List<Notification> notifications;

	public NotificationMessage() {
		super();
	}

	public NotificationMessage(long id, String message, List<Notification> notifications) {
		super();
		this.id = id;
		this.message = message;
		this.notifications = notifications;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
