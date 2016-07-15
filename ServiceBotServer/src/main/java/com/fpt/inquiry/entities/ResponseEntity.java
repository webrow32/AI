package com.fpt.inquiry.entities;

public class ResponseEntity<T> {
	public static final int RESPONSE_OK = 0;
	public static final int RESPONSE_NG = -1;
	
	private int responseCode;
	private T entity;
	
	public T getEntity() {
		return entity;
	}public int getResponseCode() {
		return responseCode;
	}public void setEntity(T entity) {
		this.entity = entity;
	}public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
}
