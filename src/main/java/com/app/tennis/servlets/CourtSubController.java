package com.app.tennis.servlets;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Court;
import com.app.tennis.exceptions.DAOException;
import com.google.gson.Gson;

public class CourtSubController {
	
	private DAO<Court> courtDao;
	DAOFactory daoFactory;
	private Court court;
	private List<Court> listeCourts;
	private String json;

	
	public CourtSubController(HttpServletRequest request) throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		ServletContext application = request.getServletContext();
		this.daoFactory = (DAOFactory) application.getAttribute("daoFactory");
		courtDao = daoFactory.getObjDAO(Court.class);
		
		String strAction = request.getParameter("action");
		
		if (strAction!=null){
			
			switch (strAction){
			case "create" :
				create(request);
				json = new Gson().toJson(court);
				break;
			case "liste" :
				this.listeCourts = listAll();
				json = new Gson().toJson(this.listeCourts);
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
		int id_court = Integer.parseInt(request.getParameter("id"));
		courtDao.delete(id_court);
	}

	private void create(HttpServletRequest request) throws DAOException {
		Court newcourt = new Court();
		newcourt.setNom(request.getParameter("nom"));
		this.court = courtDao.create(newcourt);
	}

	public List<Court> listAll() throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return courtDao.listAll();
	}
	
	public String getJson() {
		return json;
	}
}
