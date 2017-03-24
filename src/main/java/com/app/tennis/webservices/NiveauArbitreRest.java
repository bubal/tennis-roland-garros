package com.app.tennis.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.services.NiveauArbitreService;

@RestController
@RequestMapping(value="/api/niveaux")
@CrossOrigin
public class NiveauArbitreRest {

	@Autowired
	NiveauArbitreService niveauService;

	@RequestMapping(method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<NiveauArbitre> get(){
		return niveauService.listAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public NiveauArbitre get(@PathVariable int id){
		return niveauService.findById(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void del(@PathVariable int id){
		niveauService.deleteById(id);
	}

	@RequestMapping(method=RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void add(
			@RequestParam("nom") String nom,
			@RequestParam("description") String description)
	{
		
		NiveauArbitre obj = new NiveauArbitre(nom,description);
		obj = niveauService.create(obj);
	}

}
