package org.study.spring.model.message.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.spring.model.message.dto.MessageDTO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void create(MessageDTO dto) {
		sqlSession.insert("message.insert", dto);
	}

	@Override
	public MessageDTO read(int messageID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(int messageID) {
		// TODO Auto-generated method stub

	}

}
