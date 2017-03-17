package com.app.tennis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.tennis.data.Court;
import com.app.tennis.services.CourtService;

@Controller
@RequestMapping("/courts")
public class CourtController {
	
	@Autowired
	private CourtService courtService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView page(){
		ModelAndView mav = new ModelAndView();
		List<Court> courts = courtService.listAll();
		mav.addObject("listingCourts", courts);
		mav.setViewName("court");
		return mav;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView create( @RequestParam("nom") String nom) {
		Court court = new Court(nom);
		court = courtService.create(court);
		return page();
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("id") int id){
		courtService.deleteById(id);
		return page();
	}
}
