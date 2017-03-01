package com.app.tennis.servlets;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Joueur;
import com.app.tennis.data.Pays;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.exceptions.DAOException;
import com.google.gson.Gson;

public class JoueurSubController {
	
	
	private DAO<Joueur> joueurDao;
	DAOFactory daoFactory;
	private Joueur joueur;
	private List<Joueur> listeJoueurs;
	private String json;
	
	
	public JoueurSubController(HttpServletRequest request) throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		super();

		/* Initialisation du DAO */
		ServletContext application = request.getServletContext();
		this.daoFactory = (DAOFactory) application.getAttribute("daoFactory");
		joueurDao = daoFactory.getObjDAO(Joueur.class);
		
		String strAction = request.getParameter("action");
		
		if (strAction!=null){
			
			switch (strAction){
			case "create" :
				create(request);
				json = new Gson().toJson(joueur);
				break;
			case "liste" :
				this.listeJoueurs = listAll();
				json = new Gson().toJson(this.listeJoueurs);
				break;
			case "delete" :
				delete(request);
				json = new Gson().toJson(listAll());
				break;
			default:
			}
		}
		
	}

	private void create(HttpServletRequest request) throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Joueur newjoueur = new Joueur();
		DAO<Pays> paysDao = daoFactory.getObjDAO(Pays.class);
		DAO<TypeQualification> qualificationDao= daoFactory.getObjDAO(TypeQualification.class);
		
		int id_pays = Integer.parseInt(request.getParameter("pays"));
		int id_qualification = Integer.parseInt(request.getParameter("qualification"));
		
	
		Long num;
		try {
			num = Long.parseLong(request.getParameter("classement"));
		} catch (NumberFormatException e) {
			num = 0L;
		}
	
		newjoueur.setNom(request.getParameter("nom"));
		newjoueur.setPrenom(request.getParameter("prenom"));
		newjoueur.setSexe(request.getParameter("sexe"));
		newjoueur.setPays(paysDao.findById(id_pays));
		newjoueur.setQualification(qualificationDao.findById(id_qualification));
		newjoueur.setClassement(num);
		this.joueur = joueurDao.create(newjoueur);
	}

	public List<Joueur> listAll() throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return joueurDao.listAll();
	}

	private void delete(HttpServletRequest request) throws DAOException {
		
		int id_joueur = Integer.parseInt(request.getParameter("id"));
		joueurDao.deleteById(id_joueur);
		
	}


	public List<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public String getJson() {
		return json;
	}
	
}
