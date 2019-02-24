package org.study.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	// 시작 페이지로 이동
	@RequestMapping("/")
	public String main(Model model) {
		// Model : Data를 담는 그릇
		// model.addAttribute("변수명", 값)
		model.addAttribute("message", "Welcome to my shoppingMall");
		return "main"; // main.jsp
	}
}
