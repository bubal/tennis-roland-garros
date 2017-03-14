package com.app.tennis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("sessionUser")
public class DefaultController {

	@RequestMapping("/")
	public ModelAndView indexPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
}
