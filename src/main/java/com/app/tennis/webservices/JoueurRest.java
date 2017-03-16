package com.app.tennis.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.tennis.data.Joueur;
import com.app.tennis.services.JoueurService;

@RestController
@RequestMapping(value="/api/joueurs")
public class JoueurRest {

	@Autowired
	JoueurService serviceJoueur;

	@RequestMapping(method=RequestMethod.GET)
	public List<Joueur> getJoueurs(){
		return serviceJoueur.listAllFetchForRest();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Joueur getJoueur(@PathVariable int id){
		return serviceJoueur.findByIdFetchForRest(id);
	}



}
