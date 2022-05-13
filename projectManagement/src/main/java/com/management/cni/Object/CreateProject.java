package com.management.cni.Object;

import java.util.List;

import com.management.cni.Entity.Project;
import com.management.cni.Entity.User;

public class CreateProject {
	private Project project;
	private List<User> users;
	
	public CreateProject() {
		super();
	}
	
	public CreateProject(Project project, List<User> users) {
		super();
		this.project = project;
		this.users = users;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
