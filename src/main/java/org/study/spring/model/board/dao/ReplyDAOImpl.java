package org.study.spring.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.spring.model.board.dto.ReplyDTO;

import oracle.net.aso.h;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<ReplyDTO> list(Integer bno, int start, int end) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("bno", bno);
		return sqlSession.selectList("reply.listReply", map) ;
	}

	@Override
	public int count(int bno) {
		return sqlSession.selectOne("reply.count", bno);
	}

	@Override
	public void insert(ReplyDTO dto) {
		sqlSession.insert("reply.insertReply", dto);
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
