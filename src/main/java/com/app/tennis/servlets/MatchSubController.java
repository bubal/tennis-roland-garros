package com.app.tennis.servlets;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Arbitre;
import com.app.tennis.data.Court;
import com.app.tennis.data.Joueur;
import com.app.tennis.data.Match;
import com.app.tennis.data.Tournoi;
import com.app.tennis.exceptions.DAOException;
import com.google.gson.Gson;

public class MatchSubController {
	
	
	private DAO<Match> matchDao;
	DAO<Joueur> joueurDao;
	DAO<Court> courtDao;
	DAO<Arbitre> arbitreDao;
	DAO<Tournoi> tournoiDao;
	DAOFactory daoFactory;
	private Match match;
	private List<Match> listeMatchs;
	private List<Court> listeCourts;
	private List<Joueur> listeJoueurs;
	private List<Arbitre> listeArbitres;
	private String json;
	
	
	public MatchSubController(HttpServletRequest request) throws DAOException, ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		super();

		/* Initialisation du DAO */
		ServletContext application = request.getServletContext();
		this.daoFactory = (DAOFactory) application.getAttribute("daoFactory");
		matchDao = daoFactory.getObjDAO(Match.class);
		joueurDao = daoFactory.getObjDAO(Joueur.class);
		courtDao = daoFactory.getObjDAO(Court.class);
		arbitreDao = daoFactory.getObjDAO(Arbitre.class);
		tournoiDao = daoFactory.getObjDAO(Tournoi.class);
		
		listeCourts = courtDao.listAll();
		listeJoueurs = joueurDao.listAll();
		listeArbitres = arbitreDao.listAll();
		
		String strAction = request.getParameter("action");
		
		if (strAction!=null){
			
			switch (strAction){
			case "create" :
				create(request);
				json = new Gson().toJson(match);
				break;
			case "liste" :
				this.listeMatchs = listAll();
				json = new Gson().toJson(this.listeMatchs);
				break;
			case "delete" :
				delete(request);
				json = new Gson().toJson(listAll());
				break;
			default:
			}
		}
		
	}

	private void create(HttpServletRequest request) throws DAOException, ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Match newmatch = new Match();
		
		int id_joueur1 = Integer.parseInt(request.getParameter("joueur1"));
		int id_joueur2 = Integer.parseInt(request.getParameter("joueur2"));
		int id_court = Integer.parseInt(request.getParameter("court"));
		int id_arbitre = Integer.parseInt(request.getParameter("arbitre"));
		int id_tournoi = Integer.parseInt(request.getParameter("tournoi"));
		
		//DateTimeFormatter formatSaisie = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//DateTimeFormatter formatDb = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		SimpleDateFormat formatSaisie = new SimpleDateFormat("dd/MM/yyyy");
		
		//LocalDate dateDb = LocalDate.parse(request.getParameter("date"), formatSaisie);
		Date dateDb = formatSaisie.parse(request.getParameter("date"));
	
		newmatch.setTournoi(tournoiDao.findById(id_tournoi));
		newmatch.setArbitre(arbitreDao.findById(id_arbitre));
		newmatch.setCourt(courtDao.findById(id_court));
		newmatch.setJoueur1(joueurDao.findById(id_joueur1));
		newmatch.setJoueur2(joueurDao.findById(id_joueur2));
		newmatch.setSets_joueur1(0L);
		newmatch.setSets_joueur2(0L);
		newmatch.setDate(dateDb);
		this.match = matchDao.create(newmatch);
	}

	public List<Match> listAll() throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return matchDao.listAll();
	}

	public List<Court> getListeCourts() throws DAOException {
		return this.listeCourts;
	}

	public List<Joueur> getListeJoueurs() throws DAOException {
		return this.listeJoueurs;
	}

	public List<Arbitre> getListeArbitres() throws DAOException {
		return this.listeArbitres;
	}

	private void delete(HttpServletRequest request) throws DAOException {
		
		int id_match = Integer.parseInt(request.getParameter("id"));
		matchDao.deleteById(id_match);
		
	}

	public List<Match> getListeMatcchs() {
		return listeMatchs;
	}

	public String getJson() {
		return json;
	}
	
}
