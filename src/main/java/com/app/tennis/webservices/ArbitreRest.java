package com.app.tennis.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tennis.data.Arbitre;
import com.app.tennis.services.ArbitreService;
import com.app.tennis.services.NiveauArbitreService;
import com.app.tennis.services.PaysService;

@RestController
@RequestMapping(value="/api/arbitres")
public class ArbitreRest {

	@Autowired
	private ArbitreService arbitreService;
	@Autowired
	private PaysService paysService;
	@Autowired
	private NiveauArbitreService niveauService;

	@RequestMapping(method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Arbitre> getArbitres(){
		return arbitreService.listAllFetchForRest();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Arbitre getArbitre(@PathVariable int id){
		return arbitreService.findByIdFetchForRest(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void delArbitre(@PathVariable int id){
		arbitreService.deleteById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void addJoueur(
			@RequestParam("nom") String nom,
			@RequestParam("prenom") String prenom,
			@RequestParam("sexe") String sexe,
			@RequestParam("pays") int id_pays,
			@RequestParam("niveau") int id_niveau){
		
		Arbitre arbitre = new Arbitre(
				nom,
				prenom,
				sexe,
				paysService.findById(id_pays),
				niveauService.findById(id_niveau));
		
		arbitre = arbitreService.create(arbitre);
	}

}
