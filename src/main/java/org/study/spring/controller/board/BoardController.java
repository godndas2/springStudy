package org.study.spring.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.study.spring.model.board.dto.BoardDTO;
import org.study.spring.service.board.BoardService;
import org.study.spring.service.board.Paging;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int curPage, 
			@RequestParam(defaultValue = "all") String srchOption, 
			@RequestParam(defaultValue = "") String keyword) throws Exception {

		// ���ڵ� ����
		int count = boardService.cntArticle(srchOption, keyword);
		// ������ ������
		Paging paging = new Paging(count, curPage);
		int start = paging.getPageBegin();
		int end = paging.getPageEnd();
		List<BoardDTO> list = boardService.listAll(start, end, srchOption, keyword);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/list");
		Map<String, Object> map = new HashMap<>(); // map�� ��� ���� : �����Ͱ� ���� �� �ַ� map�� ��Ƽ� ó���մϴ�
		map.put("list", list); // map�� �ڷ� ����
		map.put("count", count); 
		map.put("srchOption", srchOption); 
		map.put("keyword", keyword); 
		map.put("paging", paging); 
		mv.addObject("map",map); // ������ ����
		return mv;
	}
	
	@RequestMapping("insertForm")
	public String insertForm() {
		return "board/insert";
	}

	@RequestMapping("insert")
	public String insert(@ModelAttribute BoardDTO dto, HttpSession session) throws Exception{
		
		String writer = (String) session.getAttribute("userid");
			
		dto.setWriter(writer);
		// ���ڵ� ����
		boardService.insert(dto);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno, @RequestParam int curPage, @RequestParam String srchOption, @RequestParam String keyword, HttpSession session) throws Exception {
		
		boardService.viewCnt(bno);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/view");
		mv.addObject("dto", boardService.read(bno));
		mv.addObject("curPage", curPage);
		mv.addObject("srchOption", srchOption);
		mv.addObject("keyword", keyword);
		return mv;
	}
}
