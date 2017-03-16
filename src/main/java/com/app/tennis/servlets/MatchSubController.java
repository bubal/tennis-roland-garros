//package com.app.tennis.servlets;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.app.tennis.data.Arbitre;
//import com.app.tennis.data.Court;
//import com.app.tennis.data.Joueur;
//import com.app.tennis.data.Match;
//import com.app.tennis.exceptions.DAOException;
//import com.app.tennis.services.ArbitreService;
//import com.app.tennis.services.CourtService;
//import com.app.tennis.services.JoueurService;
//import com.app.tennis.services.MatchService;
//import com.app.tennis.services.TournoiService;
//import com.google.gson.Gson;
//
//public class MatchSubController {
//	
//	@Autowired
//	private MatchService matchService;
//	@Autowired
//	private JoueurService joueurService;
//	@Autowired
//	private CourtService courtService;
//	@Autowired
//	private ArbitreService arbitreService;
//	@Autowired
//	private TournoiService tournoiService;
//	
//	private Match match;
//	private List<Match> listeMatchs;
//	private List<Court> listeCourts;
//	private List<Joueur> listeJoueurs;
//	private List<Arbitre> listeArbitres;
//	private String json;
//	
//	
//	public MatchSubController(HttpServletRequest request) throws Exception {
//		super();
//		
//		listeCourts = courtService.listAll();
//		listeJoueurs = joueurService.listAll();
//		listeArbitres = arbitreService.listAll();
//		
//		String strAction = request.getParameter("action");
//		
//		if (strAction!=null){
//			
//			switch (strAction){
//			case "create" :
//				create(request);
//				json = new Gson().toJson(match);
//				break;
//			case "liste" :
//				this.listeMatchs = listAll();
//				json = new Gson().toJson(this.listeMatchs);
//				break;
//			case "delete" :
//				delete(request);
//				json = new Gson().toJson(listAll());
//				break;
//			default:
//			}
//		}
//		
//	}
//
//	private void create(HttpServletRequest request) throws Exception {
//		Match newmatch = new Match();
//		
//		int id_joueur1 = Integer.parseInt(request.getParameter("joueur1"));
//		int id_joueur2 = Integer.parseInt(request.getParameter("joueur2"));
//		int id_court = Integer.parseInt(request.getParameter("court"));
//		int id_arbitre = Integer.parseInt(request.getParameter("arbitre"));
//		int id_tournoi = Integer.parseInt(request.getParameter("tournoi"));
//		
//		SimpleDateFormat formatSaisie = new SimpleDateFormat("dd/MM/yyyy");
//		
//		Date dateDb = formatSaisie.parse(request.getParameter("date"));
//	
//		newmatch.setTournoi(tournoiService.findById(id_tournoi));
//		newmatch.setArbitre(arbitreService.findById(id_arbitre));
//		newmatch.setCourt(courtService.findById(id_court));
//		newmatch.setJoueur1(joueurService.findById(id_joueur1));
//		newmatch.setJoueur2(joueurService.findById(id_joueur2));
//		newmatch.setSets_joueur1(0);
//		newmatch.setSets_joueur2(0);
//		newmatch.setDate(dateDb);
//		this.match = matchService.create(newmatch);
//	}
//
//	public List<Match> listAll() throws Exception {
//		return matchService.listAll();
//	}
//
//	public List<Court> getListeCourts() throws DAOException {
//		return this.listeCourts;
//	}
//
//	public List<Joueur> getListeJoueurs() throws DAOException {
//		return this.listeJoueurs;
//	}
//
//	public List<Arbitre> getListeArbitres() throws DAOException {
//		return this.listeArbitres;
//	}
//
//	private void delete(HttpServletRequest request) throws DAOException {
//		
//		int id_match = Integer.parseInt(request.getParameter("id"));
//		matchService.deleteById(id_match);
//		
//	}
//
//	public List<Match> getListeMatcchs() {
//		return listeMatchs;
//	}
//
//	public String getJson() {
//		return json;
//	}
//	
//}
