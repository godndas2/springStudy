package org.study.spring.service.message;

import org.study.spring.model.message.dto.MessageDTO;

public interface MessageService {
	public void insertMessage(MessageDTO dto);
	public MessageDTO readMessage(String userID, int messageID);
}
