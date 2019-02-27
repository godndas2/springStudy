package org.study.spring.model.board.dao;

import java.util.List;

import org.study.spring.model.board.dto.BoardDTO;

public interface BoardDAO {

	public void deleteFile(String fullName); // ÷������ ����
	public List<String> getAttach(int bno); // ÷������ ���
	public void addAttach(String fullName); // ÷������ ����
	public void updateAttach(String fullName, int bno); // ÷������ ����
	public void insert(BoardDTO dto) throws Exception;
	public BoardDTO read(int bno) throws Exception;
	public void update(BoardDTO dto) throws Exception;
	public void delete(int bno) throws Exception;
	// (���, ����¡+�˻�)
	public List<BoardDTO> listAll (int start, int end, String srchOption, String keyword) throws Exception;
	public void viewCnt(int bno) throws Exception;
	// record count
	public int cntArticle(String srchOption, String keyword) throws Exception;
	
}
