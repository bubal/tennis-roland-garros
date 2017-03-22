package com.app.tennis.webservices;

import java.util.List;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tennis.data.Joueur;
import com.app.tennis.services.JoueurService;
import com.app.tennis.services.PaysService;
import com.app.tennis.services.TypeQualificationService;

@RestController
@RequestMapping(value="/api/joueurs")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class JoueurRest {

	@Autowired
	private JoueurService joueurService;
	@Autowired
	private PaysService paysService;
	@Autowired
	private TypeQualificationService typeQualificationService;

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
	
	@RequestMapping(method=RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void add(
			@RequestParam("nom") String nom,
			@RequestParam("prenom") String prenom,
			@RequestParam("sexe") String sexe,
			@RequestParam("pays") int id_pays,
			@RequestParam("qualification") int id_qualification,
			@RequestParam("classement") String strClassement){
		
		int classement =0;
		try {
			classement = Integer.parseInt(strClassement);
		} catch (NumberFormatException e) {}
		
		Joueur obj = new Joueur(
				nom,
				prenom,
				sexe,
				paysService.findById(id_pays),
				classement,
				typeQualificationService.findById(id_qualification));
		
		obj = joueurService.create(obj);
	}

}
