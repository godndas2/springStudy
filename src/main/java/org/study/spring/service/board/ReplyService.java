package org.study.spring.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.study.spring.model.board.dto.ReplyDTO;

public interface ReplyService {
	public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session);
	public int count(int bno);
	public void insert(ReplyDTO dto);
	public void update(ReplyDTO dto);
	public void delete(int rno);
	public ReplyDTO read(int rno);
}
