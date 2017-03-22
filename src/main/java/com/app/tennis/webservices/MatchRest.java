package com.app.tennis.webservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.tennis.data.Match;
import com.app.tennis.services.ArbitreService;
import com.app.tennis.services.CourtService;
import com.app.tennis.services.JoueurService;
import com.app.tennis.services.MatchService;
import com.app.tennis.services.TournoiService;

@RestController
@RequestMapping(value="/api/matchs")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class MatchRest {

	@Autowired
	private MatchService matchService;
	@Autowired
	private JoueurService joueurService;
	@Autowired
	private CourtService courtService;
	@Autowired
	private ArbitreService arbitreService;
	@Autowired
	private TournoiService tournoiService;

	@RequestMapping(method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Match> get(){
		return matchService.listAllFetchAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Match get(@PathVariable int id){
		return matchService.findByIdFetchAll(id);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void del(@PathVariable int id){
		matchService.deleteById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void add(
			@RequestParam("joueur1") int id_joueur1,
			@RequestParam("joueur2") int id_joueur2,
			@RequestParam("court") int id_court,
			@RequestParam("arbitre") int id_arbitre,
			@RequestParam("tournoi") int id_tournoi,
			@RequestParam("date") String strdate)
	{

		SimpleDateFormat formatSaisie = new SimpleDateFormat("dd/MM/yyyy");
		Match obj = new Match();
		
		try {
			Date dateDb = formatSaisie.parse(strdate);
			obj.setDate(dateDb);
		} catch (ParseException e) {
		}
		obj.setTournoi(tournoiService.findById(id_tournoi));
		obj.setArbitre(arbitreService.findByIdFetchAll(id_arbitre));
		obj.setCourt(courtService.findById(id_court));
		obj.setJoueur1(joueurService.findByIdFetchAll(id_joueur1));
		obj.setJoueur2(joueurService.findByIdFetchAll(id_joueur2));
		obj.setSets_joueur1(0);
		obj.setSets_joueur2(0);
		
		obj = matchService.create(obj);
	}

}
