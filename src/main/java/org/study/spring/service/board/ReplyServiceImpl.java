package org.study.spring.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.spring.model.board.dao.ReplyDAO;
import org.study.spring.model.board.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyDAO replyDao;
	
	@Override
	public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session) {
		return replyDao.list(bno, start, end);
	}

	@Override
	public int count(int bno) {
		return replyDao.count(bno);
	}

	@Override
	public void insert(ReplyDTO dto) {
		replyDao.insert(dto);
	}

	@Override
	public void update(ReplyDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int rno) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReplyDTO read(int rno) {
		// TODO Auto-generated method stub
		return null;
	}

}
