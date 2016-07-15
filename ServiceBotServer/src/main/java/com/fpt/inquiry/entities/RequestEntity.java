package com.fpt.inquiry.entities;

public class RequestEntity {

	private String user;
	private String message;
	private String timestamp;
	
	public String getMessage() {
		return message;
	}public String getTimestamp() {
		return timestamp;
	}public String getUser() {
		return user;
	}public void setMessage(String message) {
		this.message = message;
	}public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}public void setUser(String user) {
		this.user = user;
	}	
}
