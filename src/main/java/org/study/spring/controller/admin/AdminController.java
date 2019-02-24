package org.study.spring.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.spring.model.member.dto.MemberDTO;
import org.study.spring.service.admin.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping("login")
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping("loginCheck")
	public ModelAndView loginCheck(HttpSession session, ModelAndView mv, MemberDTO dto) {
		String name = adminService.loginCheck(dto);
		if (name != null) { // �α��� ����
			session.setAttribute("adminUserId", dto.getUserid());
			session.setAttribute("adminName", name);
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mv.setViewName("admin/admin");
			mv.addObject("message","success");
		} else {
			mv.setViewName("admin/login");
			mv.addObject("message","error");
		}
		return mv;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}
}
