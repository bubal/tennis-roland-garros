package com.app.tennis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.tennis.data.Arbitre;
import com.app.tennis.services.ArbitreService;
import com.app.tennis.services.NiveauArbitreService;
import com.app.tennis.services.PaysService;

@Controller
@RequestMapping("/arbitres")
public class ArbitreController {
	
	@Autowired
	private ArbitreService arbitreService;
	@Autowired
	private PaysService paysService;
	@Autowired
	private NiveauArbitreService niveauService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView page(){
		ModelAndView mav = new ModelAndView();
		List<Arbitre> arbitres = arbitreService.listAllFetchAll();
		mav.addObject("listingArbitres", arbitres);
		mav.setViewName("arbitre");
		return mav;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView create( 
			@RequestParam("nom") String nom,
			@RequestParam("prenom") String prenom,
			@RequestParam("sexe") String sexe,
			@RequestParam("pays") int id_pays,
			@RequestParam("niveau") int id_niveau)
	{
		
		Arbitre arbitre = new Arbitre(
				nom,
				prenom,
				sexe,
				paysService.findById(id_pays),
				niveauService.findById(id_niveau));
		
		arbitre = arbitreService.create(arbitre);
		return page();
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("id") int id){
		arbitreService.deleteById(id);
		return page();
	}
}
