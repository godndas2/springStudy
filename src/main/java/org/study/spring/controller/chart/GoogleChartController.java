package org.study.spring.controller.chart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/chart/*")
public class GoogleChartController {

	@RequestMapping("googleChart")
	public ModelAndView chart() {
		return new ModelAndView("chart/googleChartPage");
	}
}
