package org.study.spring.service.board;

import java.util.List;

import org.study.spring.model.board.dto.BoardDTO;

public interface BoardService {
	public void deleteFile(String fullName); // 첨부파일 삭제
	public List<String> getAttach(int bno); // 첨부파일 목록
	public void addAttach(String fullName); // 첨부파일 저장
	public void updateAttach(String fullName, int bno); // 첨부파일 수정
	public void insert(BoardDTO dto) throws Exception;
	public BoardDTO read(int bno) throws Exception;
	public void update(BoardDTO dto) throws Exception;
	public void delete(int bno) throws Exception;
	// (목록, 페이징+검색)
	public List<BoardDTO> listAll (int start, int end, String srchOption, String keyword) throws Exception;
	public void viewCnt(int bno) throws Exception;
	// record count
	public int cntArticle(String srchOption, String keyword) throws Exception;
}
