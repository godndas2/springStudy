package org.study.spring.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.spring.model.message.dao.MessageDAO;
import org.study.spring.model.message.dao.PointDAO;
import org.study.spring.model.message.dto.MessageDTO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageDAO messageDao;
	@Autowired
	PointDAO pointDao;
	
	@Transactional // method ������ �ڵ带 Ʈ��������� ����
	@Override
	public void insertMessage(MessageDTO dto) {
		messageDao.create(dto);
		pointDao.point(dto.getSender(), 10);
	}

	@Override
	public MessageDTO readMessage(String userID, int messageID) {
		return null;
	}

}
