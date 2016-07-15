package com.fpt.inquiry.util;

import java.util.Date;

import com.fpt.inquiry.entities.Message;
import com.ibm.watson.developer_cloud.conversation.v1_experimental.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1_experimental.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1_experimental.model.MessageResponse;

public class ConversationUtility {
	
	private ConversationService conversationService;

	private String workspaceId;

	public String getWorkspaceId() {
		return workspaceId;
	}

	public void setWorkspaceId(String workspaceId) {
		this.workspaceId = workspaceId;
	}

	public ConversationService getConversationService() {
		return conversationService;
	}
	public void setConversationService(ConversationService conversationService) {
		this.conversationService = conversationService;
	}
	
	public Message sendMessage(String text) {
		MessageRequest.Builder build = new MessageRequest.Builder();
		build.inputText(text);
		MessageRequest mess = build.build();

		MessageResponse response = conversationService.message(workspaceId, mess)
				.execute();

		return messageResponseToMessage(response);
	}
	
	private Message messageResponseToMessage(MessageResponse response){
		Message mess = new Message();
		mess.setMessage(response.getText().substring(1,response.getText().length()-1));
		mess.setTimestamp(new Date().toString());
		
		return mess;
	}
	
}
