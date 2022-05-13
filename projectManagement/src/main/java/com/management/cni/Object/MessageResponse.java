package com.management.cni.Object;

import java.io.Serializable;

public class MessageResponse implements Serializable{
	private String message;
	private String type;
	public MessageResponse(String message, String type) {
		super();
		this.message = message;
		this.type = type;
	}
	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}

	
}
