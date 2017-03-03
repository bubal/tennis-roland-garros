package com.app.tennis.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ControlServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String task = request.getParameter("task");
		
		RequestDispatcher rd = null;
		
		/* Si on est connecté regarde la tache demandée */
		if (task==null){
			task = "home";
		}
		
		switch(task){
		
		case "deconnexion" :
			ConnexionSubController deconnexion = new ConnexionSubController(request);
			deconnexion.deconnecting();
			rd = request.getRequestDispatcher("deconnexion.jsp");
			request.setAttribute("errorMsg", "Déconnecté");
			break;	
			
		case "connexion" :
			ConnexionSubController connexion = new ConnexionSubController(request);
			connexion.connecting();
			rd = request.getRequestDispatcher(connexion.getPageVue());
			request.setAttribute("errorMsg", connexion.getUser().getError());
			break;
			
		case "home" :
			rd = request.getRequestDispatcher("index.jsp");
			break;
			
		case "joueur" :
			try {
				JoueurSubController joueurControl = new JoueurSubController(request);
				request.setAttribute("listingJoueurs",joueurControl.listAll());
			} catch (Exception e) {
				request.setAttribute("errorMsg", e.getMessage());
			}
			rd = request.getRequestDispatcher("joueur.jsp");
			break;
			
		case "arbitre" :
			try {
				ArbitreSubController arbitreControl = new ArbitreSubController(request);
				request.setAttribute("listingArbitres",arbitreControl.listAll());
			} catch (Exception e) {
				request.setAttribute("errorMsg", e.getMessage());
			}
			rd = request.getRequestDispatcher("arbitre.jsp");
			break;
		case "court" :
			try {
				CourtSubController courtControl = new CourtSubController(request);
				request.setAttribute("listingCourts",courtControl.listAll());
			} catch (Exception e) {
				request.setAttribute("errorMsg", e.getMessage());
			}
			rd = request.getRequestDispatcher("court.jsp");
			break;
		case "match" :
			try {
				MatchSubController matchControl = new MatchSubController(request);
				request.setAttribute("listingMatchs",matchControl.listAll());
				request.setAttribute("listingJoueurs",matchControl.getListeJoueurs());
				request.setAttribute("listingCourts",matchControl.getListeCourts());
				request.setAttribute("listingArbitres",matchControl.getListeArbitres());
			} catch (Exception e) {
				request.setAttribute("errorMsg", e.getMessage());
			}
			rd = request.getRequestDispatcher("match.jsp");
			break;
		case "resultat" :
			rd = request.getRequestDispatcher("resultat.jsp");
			break;
		default:
			rd = request.getRequestDispatcher("index.jsp");
		}
		
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
