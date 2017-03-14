package com.app.tennis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.tennis.data.Acces;
import com.app.tennis.services.AccesService;

@Controller
@SessionAttributes("sessionUser")
@RequestMapping("/connexion")
public class ConnexionController {
	
	@Autowired
	private AccesService accesService;
	
	private Acces user;
	
	/* Affichage de la page de login */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView connectPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("connexion");
		return mav;
	}
	
	/* Contrôle du formulaire de login */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView connecting( 
			@RequestParam("login") String login,
			@RequestParam("password") String password)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("connexion");
		user = new Acces(login);
		user = accesService.grantedAcces(login, password);
		mav.addObject("errorMsg", user.getError());
		mav.addObject("sessionUser", "error_login");
		if (user.isAcces()){
			mav.setViewName("index");
			mav.addObject("sessionUser", user.getLogin());
		} 
		return mav;
	}
}
