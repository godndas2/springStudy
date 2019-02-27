package org.study.spring.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.study.spring.model.board.dto.BoardDTO;
import org.study.spring.model.board.dto.ReplyDTO;
import org.study.spring.service.board.Paging;
import org.study.spring.service.board.ReplyService;

@RestController
@RequestMapping("/reply/*")
public class ReplyController {

	@Autowired
	ReplyService replyService;
	
	@RequestMapping("insert")
	public void insert(ReplyDTO dto, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		dto.setReplyer(userid);
		replyService.insert(dto);
	}
	
	@RequestMapping("list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int curPage, int bno, HttpSession session, ModelAndView mv) throws Exception {

		int count = replyService.count(bno);
		Paging paging = new Paging(count, curPage);
		int start = paging.getPageBegin();
		int end = paging.getPageEnd();
		List<ReplyDTO> list = replyService.list(bno, start, end, session);
		
		mv.setViewName("board/replyList");
		mv.addObject("list", list); // map에 자료 저장
		mv.addObject("paging", paging); 
		return mv;
	}
}
