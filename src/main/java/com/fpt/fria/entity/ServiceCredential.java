package com.fpt.fria.entity;

public class ServiceCredential {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ENDPOINT = "endpoint";


    private String username;
    private String password;
    private String endpoint;

    public String getPassword() {
        return password;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
