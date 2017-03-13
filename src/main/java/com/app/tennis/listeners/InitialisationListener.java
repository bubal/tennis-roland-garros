package com.app.tennis.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitialisationListener implements ServletContextListener {
	
//	private String NAME_PERSISTENCE = "tennis-db";
//	//private String NAME_DB = "/app-db.sql";
//	
//	List<Tournoi> listeTournois = null;
//	List<TypeQualification> listeQualifications = null;
//	List<NiveauArbitre> listeNiveauArbitre = null;
//	List<Pays> listePays = null;
	
	public InitialisationListener() {

	}

	public void contextInitialized(ServletContextEvent e) { 
		
//		ServletContext application = e.getServletContext();
//		DAOUtilities daoUtilities;
//
//		daoUtilities = (DAOUtilities) application.getAttribute("daoUtilities");
//
//		if (daoUtilities==null){
//			
//			daoUtilities = new DAOUtilities( NAME_PERSISTENCE );
//			application.setAttribute("daoFactory",daoUtilities);
//		}
//		
//		try {
//			
//			PaysService paysService                              =	new PaysServiceImpl();
//			TournoiService tournoiService                        =	new TournoiServiceImpl();
//			NiveauArbitreService niveauArbitreService            =	new NiveauArbitreServiceImpl();
//			TypeQualificationService typeQualificationService    =	new TypeQualificationServiceImpl();
//			
//			listeTournois             = tournoiService.listAll();
//			listePays                 = paysService.listAll();
//			listeQualifications       = typeQualificationService.listAll();
//			listeNiveauArbitre        = niveauArbitreService.listAll();
//			
//			
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		
//		application.setAttribute("listePays",listePays);
//		application.setAttribute("listeTournois",listeTournois);
//		application.setAttribute("listeQualifications",listeQualifications);
//		application.setAttribute("listeNiveauArbitre",listeNiveauArbitre);
		

	}

	public void contextDestroyed(ServletContextEvent e)  { 
		
//		ServletContext application = e.getServletContext();
//		DAOUtilities daoUtilities;
//
//		daoUtilities = (DAOUtilities) application.getAttribute("daoUtilities");
//
//		if (daoUtilities!=null){
//			daoUtilities.closeEntityManager();
//		}

	}

}
