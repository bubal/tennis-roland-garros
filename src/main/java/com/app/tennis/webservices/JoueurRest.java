package com.app.tennis.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.tennis.data.Joueur;
import com.app.tennis.services.JoueurService;

@RestController
@RequestMapping(value="/api/joueurs")
@CrossOrigin
public class JoueurRest {

	@Autowired
	private JoueurService joueurService;

	@RequestMapping(method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Joueur> get(){
		return joueurService.listAllFetchAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Joueur get(@PathVariable int id){
		return joueurService.findByIdFetchAll(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void del(@PathVariable int id){
		joueurService.deleteById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Joueur add(@RequestBody Joueur obj){
		return joueurService.create(obj);
	}

}
