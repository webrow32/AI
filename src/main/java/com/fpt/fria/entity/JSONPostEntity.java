package com.fpt.fria.entity;

/**
 * Created by fealrone.alajas on 7/13/2016.
 */
public class JSONPostEntity {
    private String message;
    private String timestamp;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
