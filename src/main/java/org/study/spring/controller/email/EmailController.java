package org.study.spring.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.spring.model.email.EmailDTO;
import org.study.spring.service.email.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {

	@Autowired
	EmailService emailService;
	
	
	@RequestMapping("insert")
	public String insert() {
		return "email/insert";
	}
	
	@RequestMapping("send")
	public String send(@ModelAttribute EmailDTO dto, Model model) {
		try {
			emailService.sendMail(dto);
			model.addAttribute("message","메일이 발송되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message","메일 발송이 실패하였습니다");
		}
		return "/email/insert";
	}
}
