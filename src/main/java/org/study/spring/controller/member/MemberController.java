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
		// @ModelAttribute�� �Ѳ����� �����͸� ���� �� �ִ� 
		// String id, String pwd �̷��� ���� ���� �ʰ� @ModelAttribute�� ����ؼ� 
		// MemberDTO���� �̹� �������� userid�� pwd�� ������ MemberDTO�� �����͵���  @ModelAttribute�� ����ؼ� �޾ƿ� �� �ִ�.
		
		String id = memberService.loginCheck(dto, session);
		logger.info("id : " + id);
		ModelAndView mv = new ModelAndView();
		
		if (id != null) { // login ���� �� homepage�� �̵�
			mv.setViewName("home");
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
	
	@RequestMapping("member/list") // url�� ��û�� �ּ�
	public String memberList(Model model) {
		
		// System.out.println("MemberService : " + memberService); // memberService Ȯ���غ���
		List<MemberDTO> list = memberService.memberList();
		logger.info("ȸ�� ��� : " + list);
		model.addAttribute("list", list); // model�� �����ϰ� member/memberList�� ������.
		
		return "member/memberList"; // ��µǴ� ������ : WEB-INF/views/member/memberList.jsp
	}

	@RequestMapping(value = "member/create", method = RequestMethod.GET) 
	public String memberCreate() {
		return "member/create"; // ȸ����� ������ �̵�
	}
	
	// @ModelAttribute : ��� ������ �Է��� Data�� �����Ѵ�.
	@RequestMapping(value = "member/create" ,method = RequestMethod.POST) 
	public String memberCreate(@ModelAttribute MemberDTO dto) {
		memberService.insertMember(dto);
		return "member/create"; // ��� ȭ�� ��������
	}
	
	@RequestMapping("member/insert") 
	public String memberInsert(MemberDTO dto) { // dto ������ ���ȭ�鿡 �Է��� Data�� ����ȴ�.
		memberService.insertMember(dto);
		return "redirect:/member/memberList"; // ��µǴ� ������ : WEB-INF/views/member/memberList.jsp
	}
	
	@RequestMapping("member/read")
	public String memberRead(@RequestParam String userid, Model model) { // @RequestParam : ���� �Է��� �ϳ��ϳ��� ���� �������ֱ����� �������. ���ֵ� �� ���ư���.
		
		model.addAttribute("dto", memberService.readMember(userid)); // model�� ȸ�� ������ ���� 
		return "member/read";
	}
	
	@RequestMapping("member/update") 
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		// pwd check
		boolean result = memberService.checkPwd(dto.getUserid(), dto.getPwd());
		logger.info("��й�ȣ Ȯ�� : " + result);
		
		if (result) { // pwd�� true�� ���
			memberService.updateMember(dto);
			return "redirect:/member/list"; // ���� ���� �� ������� ������
		} else {
			MemberDTO md = memberService.readMember(dto.getUserid());
			dto.setJoinDate(md.getJoinDate()); // ��¥ ����
			model.addAttribute("dto",dto);
			model.addAttribute("message","��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			return "member/read";
		}
	}

	@RequestMapping("member/delete") 
	public String delete(@RequestParam String userid, @RequestParam String pwd, Model model) {
		// pwd check
		boolean result = memberService.checkPwd(userid, pwd);
		
		if (result) { // pwd�� true�� ���
			memberService.deleteMember(userid);
			return "redirect:/member/list"; // ���� ���� �� ������� ������
		} else {
			model.addAttribute("message","��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			model.addAttribute("dto",memberService.readMember(userid));
			return "member/read";
		}
	}
	
}
