package org.study.spring.model.message.dao;

import org.study.spring.model.message.dto.MessageDTO;

public interface MessageDAO {
	public void create(MessageDTO dto);
	public MessageDTO read(int messageID);
	public void updateState(int messageID);
}
