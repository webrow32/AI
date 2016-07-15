package com.fpt.inquiry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.inquiry.entities.ResponseEntity;
import com.fpt.inquiry.entities.ServiceCredential;


@Service
public class CredentialService {

	@Autowired
	private ServiceCredential speechToTextCredential;
	
	@Autowired
	private ServiceCredential textToSpeechCredential;
	
	public ResponseEntity<ServiceCredential> getSTTCreds(){
		ResponseEntity<ServiceCredential> flag = new ResponseEntity<ServiceCredential>();
		flag.setEntity(speechToTextCredential);
		flag.setResponseCode(ResponseEntity.RESPONSE_OK);
		return flag;
	}
	
	public ResponseEntity<ServiceCredential> getTTSCreds(){
		ResponseEntity<ServiceCredential> flag = new ResponseEntity<ServiceCredential>();
		flag.setEntity(textToSpeechCredential);
		flag.setResponseCode(ResponseEntity.RESPONSE_OK);
		return flag;
	}
}
