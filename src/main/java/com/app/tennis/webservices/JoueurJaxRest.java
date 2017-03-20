package com.app.tennis.webservices;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import com.app.tennis.data.Joueur;
import com.app.tennis.services.JoueurService;
import com.app.tennis.services.PaysService;
import com.app.tennis.services.TypeQualificationService;


@Path("/joueurs")
@Produces("application/json")
@Component
public class JoueurJaxRest {

	@Autowired
	JoueurService joueurService;
	@Autowired
	private PaysService paysService;
	@Autowired
	private TypeQualificationService typeQualificationService;

	@GET
	public List<Joueur> getJoueurs(){
		return joueurService.listAllFetchAll();
	}

	@GET
	@Path("/{id}")
	public Joueur getJoueur(@PathParam("id") int id){
		return joueurService.findByIdFetchAll(id);
	}

	@DELETE
	@Path("/{id}")
	public void delJoueur(@PathParam("id") int id){
		joueurService.deleteById(id);
	}
	
	@POST
	public void addJoueur(
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
		
		Joueur joueur = new Joueur(
				nom,
				prenom,
				sexe,
				paysService.findById(id_pays),
				classement,
				typeQualificationService.findById(id_qualification));
		
		joueur = joueurService.create(joueur);
	}

}
