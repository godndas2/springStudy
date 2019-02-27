package org.study.spring.model.board.dao;

import java.util.List;

import org.study.spring.model.board.dto.ReplyDTO;

public interface ReplyDAO {
	public List<ReplyDTO> list(Integer bno, int start, int end);
	public int count(int bno);
	public void insert(ReplyDTO dto);
	public void update(ReplyDTO dto);
	public void delete(int rno);
	public ReplyDTO read(int rno);
}
