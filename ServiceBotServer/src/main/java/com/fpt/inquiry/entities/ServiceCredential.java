package com.fpt.inquiry.entities;

public class ServiceCredential {

	private String username;
	private String password;
	private String endpoint;

	public String getEndpoint() {
		return endpoint;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
