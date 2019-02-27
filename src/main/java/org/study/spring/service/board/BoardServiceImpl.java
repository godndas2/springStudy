package org.study.spring.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.spring.model.board.dao.BoardDAO;
import org.study.spring.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDao;
	
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
		boardDao.insert(dto);
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return boardDao.read(bno);
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
		return boardDao.listAll(start, end, srchOption, keyword);
	}

	@Override
	public void viewCnt(int bno) throws Exception {
		boardDao.viewCnt(bno);
	}

	@Override
	public int cntArticle(String srchOption, String keyword) throws Exception {
		return boardDao.cntArticle(srchOption, keyword);
	}

}
