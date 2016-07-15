package com.fpt.inquiry.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.inquiry.entities.ResponseEntity;
import com.fpt.inquiry.entities.ServiceCredential;
import com.fpt.inquiry.service.CredentialService;

@RequestMapping(value="/credential")
@RestController
public class CredentialsController {

	@Autowired
	private CredentialService credentialService;
	
	@RequestMapping(value="/speechtotext", method= RequestMethod.GET)
	public ResponseEntity<ServiceCredential> getSTTCreds(){
		return credentialService.getSTTCreds();
	}
	
	@RequestMapping(value="/texttospeech", method= RequestMethod.GET)
	public ResponseEntity<ServiceCredential> getTTSCreds(){
		return credentialService.getTTSCreds();
	}
}
