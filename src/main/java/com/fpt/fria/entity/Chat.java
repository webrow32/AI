package com.fpt.fria.entity;

/**
 * Created by Resty Louis Artiaga on 7/13/2016.
 */
public class Chat {
    public static final int FROM_BOT = 1;
    public static final int FROM_YOU = 2;

    private String message;
    private String timestamp;
    private int from;

    public int getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
