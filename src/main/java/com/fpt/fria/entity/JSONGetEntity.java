package com.fpt.fria.entity;

import java.util.Map;

/**
 * Created by fealrone.alajas on 7/13/2016.
 */
public class JSONGetEntity {

    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";

    public int responseCode;
    public Map<String, String> entity;

    public Map<String, String> getEntity() {
        return entity;
    }

    public void setEntity(Map<String, String> entity) {
        this.entity = entity;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }


}
