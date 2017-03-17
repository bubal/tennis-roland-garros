package com.app.tennis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.tennis.data.Joueur;
import com.app.tennis.services.JoueurService;
import com.app.tennis.services.PaysService;
import com.app.tennis.services.TypeQualificationService;

@Controller
@RequestMapping("/joueurs")
public class JoueurController {
	
	@Autowired
	private JoueurService joueurService;
	@Autowired
	private PaysService paysService;
	@Autowired
	private TypeQualificationService typeQualificationService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView page(){
		ModelAndView mav = new ModelAndView();
		List<Joueur> joueurs = joueurService.listAll();
		mav.addObject("listingJoueurs", joueurs);
		mav.setViewName("joueur");
		return mav;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView create( 
			@RequestParam("nom") String nom,
			@RequestParam("prenom") String prenom,
			@RequestParam("sexe") String sexe,
			@RequestParam("pays") int id_pays,
			@RequestParam("qualification") int id_qualification,
			@RequestParam("classement") String strClassement)
	{
		
		int classement =0;
		try {
			classement = Integer.parseInt(strClassement);
		} catch (NumberFormatException e) {}
		
		Joueur joueur = new Joueur(
				nom,
				prenom,
				sexe,
				paysService.findById(id_pays),
				classement,
				typeQualificationService.findById(id_qualification));
		
		joueur = joueurService.create(joueur);
		return page();
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("id") int id){
		joueurService.deleteById(id);
		return page();
	}
}
