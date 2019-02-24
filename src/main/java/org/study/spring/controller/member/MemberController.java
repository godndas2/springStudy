package org.study.spring.controller.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.study.spring.model.member.dto.MemberDTO;
import org.study.spring.service.member.MemberService;

@Controller // Controller -> Service -> DAO
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	MemberService memberService;
	
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}

	@RequestMapping("loginCheck")
	public ModelAndView loginCheck(@ModelAttribute MemberDTO dto, HttpSession session) {
		// @ModelAttribute占쏙옙 占싼뀐옙占쏙옙占쏙옙 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙 占쏙옙 占쌍댐옙 
		// String id, String pwd 占싱뤄옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占십곤옙 @ModelAttribute占쏙옙 占쏙옙占쏙옙漫占� 
		// MemberDTO占쏙옙占쏙옙 占싱뱄옙 占쏙옙占쏙옙占쏙옙占쏙옙 userid占쏙옙 pwd占쏙옙 占쏙옙占쏙옙占쏙옙 MemberDTO占쏙옙 占쏙옙占쏙옙占싶듸옙占쏙옙  @ModelAttribute占쏙옙 占쏙옙占쏙옙漫占� 占쌨아울옙 占쏙옙 占쌍댐옙.
		
		String id = memberService.loginCheck(dto, session);
		logger.info("id : " + id);
		ModelAndView mv = new ModelAndView();
		
		if (id != null) { // login 占쏙옙占쏙옙 占쏙옙 homepage占쏙옙 占싱듸옙
			mv.setViewName("main");
		} else {
			mv.setViewName("member/login");
			mv.addObject("message", "error");
		}
		return mv;
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session, ModelAndView mv) {
		
		memberService.logout(session);
		mv.setViewName("member/login");
		mv.addObject("message","logout");
		return mv;
	}
	
	@RequestMapping("member/list") // url占쏙옙 占쏙옙청占쏙옙 占쌍쇽옙
	public String memberList(Model model) {
		
		// System.out.println("MemberService : " + memberService); // memberService 확占쏙옙占쌔븝옙占쏙옙
		List<MemberDTO> list = memberService.memberList();
		logger.info("회占쏙옙 占쏙옙占� : " + list);
		model.addAttribute("list", list); // model占쏙옙 占쏙옙占쏙옙占싹곤옙 member/memberList占쏙옙 占쏙옙占쏙옙占쏙옙.
		
		return "member/memberList"; // 占쏙옙쨉풔占� 占쏙옙占쏙옙占쏙옙 : WEB-INF/views/member/memberList.jsp
	}

	@RequestMapping(value = "member/insert", method = RequestMethod.GET) 
	public String memberInsertForm() {
		return "member/insert"; // 회占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占싱듸옙
	}
	
	// @ModelAttribute : 占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쌉뤄옙占쏙옙 Data占쏙옙 占쏙옙占쏙옙占싼댐옙.
	@RequestMapping(value = "member/insert" ,method = RequestMethod.POST) 
	public String memberInsert(@ModelAttribute MemberDTO dto) {
		memberService.insertMember(dto);
		return "redirect:/member/list"; // 占쏙옙占� 화占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙
	}
	
	@RequestMapping("member/read")
	public String memberRead(@RequestParam String userid, Model model) { // @RequestParam : 占쏙옙占쏙옙 占쌉뤄옙占쏙옙 占싹놂옙占싹놂옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쌍깍옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占�. 占쏙옙占쌍듸옙 占쏙옙 占쏙옙占싣곤옙占쏙옙.
		
		model.addAttribute("dto", memberService.readMember(userid)); // model占쏙옙 회占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 
		return "member/read";
	}
	
	@RequestMapping("member/update") 
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		// pwd check
		boolean result = memberService.checkPwd(dto.getUserid(), dto.getPwd());
		logger.info("占쏙옙橘占싫� 확占쏙옙 : " + result);
		
		if (result) { // pwd占쏙옙 true占쏙옙 占쏙옙占�
			memberService.updateMember(dto);
			return "redirect:/member/list"; // 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙
		} else {
			MemberDTO md = memberService.readMember(dto.getUserid());
			dto.setJoinDate(md.getJoinDate()); // 占쏙옙짜 占쏙옙占쏙옙
			model.addAttribute("dto",dto);
			model.addAttribute("message","占쏙옙橘占싫ｏ옙占� 占쏙옙치占쏙옙占쏙옙 占십쏙옙占싹댐옙");
			return "member/read";
		}
	}

	@RequestMapping("member/delete") 
	public String delete(@RequestParam String userid, @RequestParam String pwd, Model model) {
		// pwd check
		boolean result = memberService.checkPwd(userid, pwd);
		
		if (result) { // pwd占쏙옙 true占쏙옙 占쏙옙占�
			memberService.deleteMember(userid);
			return "redirect:/member/list"; // 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙
		} else {
			model.addAttribute("message","占쏙옙橘占싫ｏ옙占� 占쏙옙치占쏙옙占쏙옙 占십쏙옙占싹댐옙");
			model.addAttribute("dto",memberService.readMember(userid));
			return "member/read";
		}
	}
	
}
