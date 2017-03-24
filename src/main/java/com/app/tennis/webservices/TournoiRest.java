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

import com.app.tennis.data.Tournoi;
import com.app.tennis.services.TournoiService;

@RestController
@RequestMapping(value="/api/tournois")
@CrossOrigin
public class TournoiRest {

	@Autowired
	TournoiService tournoiService;

	@RequestMapping(method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Tournoi> get(){
		return tournoiService.listAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Tournoi get(@PathVariable int id){
		return tournoiService.findById(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void del(@PathVariable int id){
		tournoiService.deleteById(id);
	}

	@RequestMapping(method=RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void add(
			@RequestParam("nom") String nom,
			@RequestParam("nbr_sets") int nbr_sets)
	{
		Tournoi obj = new Tournoi(nom,nbr_sets);
		obj = tournoiService.create(obj);
	}

}
