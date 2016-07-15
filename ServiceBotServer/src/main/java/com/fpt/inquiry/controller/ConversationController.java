package com.fpt.inquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.inquiry.entities.Message;
import com.fpt.inquiry.entities.RequestEntity;
import com.fpt.inquiry.entities.ResponseEntity;
import com.fpt.inquiry.service.ConversationService;


@RestController
public class ConversationController {	
		
	@Autowired
	private ConversationService convService;
		
	@RequestMapping(value="/sendMessage", method = RequestMethod.POST)
	public ResponseEntity<Message> sendMessage(@RequestBody RequestEntity request){		
		return convService.sendMessage(request.getMessage());
	}
	
	
}
