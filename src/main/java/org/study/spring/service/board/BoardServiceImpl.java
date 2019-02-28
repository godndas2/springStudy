package org.study.spring.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.spring.model.board.dao.BoardDAO;
import org.study.spring.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDao;
	
	@Override
	public void deleteFile(String fullName) {
		boardDao.deleteFile(fullName);
	}

	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno);
	}

	@Override
	public void addAttach(String fullName) {
		boardDao.addAttach(fullName);
	}

	@Override
	public void updateAttach(String fullName, int bno) {
		boardDao.updateAttach(fullName, bno);
	}
	
	@Transactional
	@Override
	public void insert(BoardDTO dto) throws Exception {
		boardDao.insert(dto);
		String [] files = dto.getFiles(); // 첨부파일 배열로 정보 저장
		// 첨부파일이 없으면 종료
		if (files == null) {
			return;
		}
		for (String name : files) {
			boardDao.addAttach(name);
		}
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	@Transactional
	@Override
	public void update(BoardDTO dto) throws Exception {
		boardDao.update(dto); // 수정
		String [] files = dto.getFiles(); // 파일 정보를 가져온다
		System.out.println("첨부파일 : "+ files);
		if (files == null) {
			return;
		}
		for (String name : files) {
			boardDao.updateAttach(name, dto.getBno()); // 첨부파일이름 , 게시물 번호
		}
	}

	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
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
