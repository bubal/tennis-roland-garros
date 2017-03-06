package com.app.tennis.servlets;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.app.tennis.dao.DAOUtilities;
import com.app.tennis.data.Court;
import com.app.tennis.exceptions.DAOException;
import com.app.tennis.services.CourtService;
import com.app.tennis.services.impl.CourtServiceImpl;
import com.google.gson.Gson;

public class CourtSubController {
	
	private CourtService courtService;
	DAOUtilities daoUtilities;
	private Court court;
	private List<Court> listeCourts;
	private String json;

	
	public CourtSubController(HttpServletRequest request) throws Exception {
		
		ServletContext application = request.getServletContext();
		this.daoUtilities = (DAOUtilities) application.getAttribute("daoUtilities");
		courtService = new CourtServiceImpl();
		
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
		courtService.deleteById(id_court);
	}

	private void create(HttpServletRequest request) throws DAOException {
		Court newcourt = new Court();
		newcourt.setNom(request.getParameter("nom"));
		this.court = courtService.create(newcourt);
	}

	public List<Court> listAll() throws Exception {
		return courtService.listAll();
	}
	
	public String getJson() {
		return json;
	}
}
