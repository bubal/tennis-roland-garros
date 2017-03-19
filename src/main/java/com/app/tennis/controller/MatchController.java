package com.app.tennis.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.tennis.data.Arbitre;
import com.app.tennis.data.Court;
import com.app.tennis.data.Joueur;
import com.app.tennis.data.Match;
import com.app.tennis.services.ArbitreService;
import com.app.tennis.services.CourtService;
import com.app.tennis.services.JoueurService;
import com.app.tennis.services.MatchService;
import com.app.tennis.services.TournoiService;

@Controller
@RequestMapping("/matchs")
public class MatchController {
	
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
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView page(){
		
		ModelAndView mav = new ModelAndView();
		
		List<Match> matchs = matchService.listAllFetchAll();
		List<Joueur> joueurs = joueurService.listAll();
		List<Court> courts = courtService.listAll();
		List<Arbitre> arbitres = arbitreService.listAll();

		mav.addObject("listingJoueurs", joueurs);
		mav.addObject("listingMatchs", matchs);
		mav.addObject("listingCourts", courts);
		mav.addObject("listingArbitres", arbitres);
		mav.setViewName("match");
		return mav;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView create( 
			@RequestParam("joueur1") int id_joueur1,
			@RequestParam("joueur2") int id_joueur2,
			@RequestParam("court") int id_court,
			@RequestParam("arbitre") int id_arbitre,
			@RequestParam("tournoi") int id_tournoi,
			@RequestParam("date") String strdate)
	{

		SimpleDateFormat formatSaisie = new SimpleDateFormat("dd/MM/yyyy");
		Match match = new Match();
		
		try {
			Date dateDb = formatSaisie.parse(strdate);
			match.setDate(dateDb);
		} catch (ParseException e) {
		}
		match.setTournoi(tournoiService.findById(id_tournoi));
		match.setArbitre(arbitreService.findByIdFetchAll(id_arbitre));
		match.setCourt(courtService.findById(id_court));
		match.setJoueur1(joueurService.findByIdFetchAll(id_joueur1));
		match.setJoueur2(joueurService.findByIdFetchAll(id_joueur2));
		match.setSets_joueur1(0);
		match.setSets_joueur2(0);
		
		match = matchService.create(match);
		return page();
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("id") int id){
		matchService.deleteById(id);
		return page();
	}
}
