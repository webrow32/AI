package com.fpt.inquiry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.inquiry.entities.Message;
import com.fpt.inquiry.entities.ResponseEntity;
import com.fpt.inquiry.util.ConversationUtility;

@Service
public class ConversationService {
	
	@Autowired
	private ConversationUtility convUtil;
	
	public ResponseEntity<Message> sendMessage(String message){
		ResponseEntity<Message> response = new ResponseEntity<Message>();
		response.setResponseCode(ResponseEntity.RESPONSE_OK);
		response.setEntity(convUtil.sendMessage(message));
		return response;
	}
}
