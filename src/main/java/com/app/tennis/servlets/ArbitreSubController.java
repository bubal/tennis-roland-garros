package com.app.tennis.servlets;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Arbitre;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.data.Pays;
import com.app.tennis.exceptions.DAOException;
import com.google.gson.Gson;

public class ArbitreSubController {
	
	private DAO<Arbitre> arbitreDao;
	DAOFactory daoFactory;
	private Arbitre arbitre;
	private List<Arbitre> listeArbitres;
	private String json;

	
	public ArbitreSubController(HttpServletRequest request) throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		ServletContext application = request.getServletContext();
		this.daoFactory = (DAOFactory) application.getAttribute("daoFactory");
		arbitreDao = daoFactory.getObjDAO(Arbitre.class);
		
		String strAction = request.getParameter("action");
		
		if (strAction!=null){
			
			switch (strAction){
			case "create" :
				create(request);
				json = new Gson().toJson(arbitre);
				break;
			case "liste" :
				this.listeArbitres = listAll();
				json = new Gson().toJson(this.listeArbitres);
				break;
			case "delete" :
				delete(request);
				json = new Gson().toJson(listAll());
				break;
			default:
			}
		}
		
	}

	private void delete(HttpServletRequest request) throws DAOException {
		int id_arbitre = Integer.parseInt(request.getParameter("id"));
		arbitreDao.deleteById(id_arbitre);
		
	}

	private void create(HttpServletRequest request) throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Arbitre newarbitre = new Arbitre();
		DAO<Pays> paysDao = daoFactory.getObjDAO(Pays.class);
		DAO<NiveauArbitre> niveauDao= daoFactory.getObjDAO(NiveauArbitre.class);
		
		int id_pays = Integer.parseInt(request.getParameter("pays"));
		int id_niveau = Integer.parseInt(request.getParameter("niveau"));
	
		newarbitre.setNom(request.getParameter("nom"));
		newarbitre.setPrenom(request.getParameter("prenom"));
		newarbitre.setSexe(request.getParameter("sexe"));
		newarbitre.setPays(paysDao.findById(id_pays));
		newarbitre.setNiveau(niveauDao.findById(id_niveau));
		this.arbitre = arbitreDao.create(newarbitre);
		
	}

	public List<Arbitre> listAll() throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return arbitreDao.listAll();
	}
	
	public String getJson() {
		return json;
	}
}
