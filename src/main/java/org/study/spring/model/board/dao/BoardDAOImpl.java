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
		sqlSession.delete("board.deleteAttach", fullName);
	}

	@Override
	public List<String> getAttach(int bno) {
		return sqlSession.selectList("board.getAttach", bno);
	}

	@Override
	public void addAttach(String fullName) {
		sqlSession.insert("board.addAttach", fullName);
	}

	@Override
	public void updateAttach(String fullName, int bno) {
		Map<String, Object> map = new HashMap<>();
		map.put("fullName", fullName);
		map.put("bno", bno);
		sqlSession.insert("board.updateAttach", map); // update인데 insert 하는 이유 : 새로운 첨부파일을 등록하기 때문
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
		sqlSession.update("board.update", dto);
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.delete", bno);
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
