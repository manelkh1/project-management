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
@Table(name = "t_status" )
public class Status {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id ;
	
	@Column(name="description")
	private String description ;
	
	@Column(name="status_code")
	private String statusCode ;
	
	@Column(name="entity_name")
	private String entityName;
	
	@Column(name="background_color")
	private String backgroundColor;
	
	@Column(name="color")
	private String color;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status")
	private List<Attachement> attachements;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status")
	private List<Invitation> invitations;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status")
	private List<Notification> notifications;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status")
	private List<Project> projects;
	
	public Status() {
		super();
	}
	public Status(long id, String description, String statusCode, String entityName, String backgroundColor,
			String color) {
		super();
		this.setId(id);
		this.setDescription(description);
		this.setStatusCode(statusCode);
		this.setEntityName(entityName);
		this.setBackgroundColor(backgroundColor);
		this.setColor(color);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
