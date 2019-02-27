package org.study.spring.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.spring.model.board.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void deleteFile(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAttach(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAttach(String fullName, int bno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(BoardDTO dto) throws Exception {
		sqlSession.insert("board.insert", dto);
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return sqlSession.selectOne("board.read", bno);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoardDTO> listAll(int start, int end, String srchOption, String keyword) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("srchOption", srchOption); 
		map.put("keyword", keyword); 
		return sqlSession.selectList("board.listAll", map);
	}

	@Override
	public void viewCnt(int bno) throws Exception {
		sqlSession.update("board.viewCnt", bno);
	}

	@Override
	public int cntArticle(String srchOption, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("srchOption", srchOption);
		map.put("keyword", keyword);
		return sqlSession.selectOne("board.cntArticle", map);
	}

}
