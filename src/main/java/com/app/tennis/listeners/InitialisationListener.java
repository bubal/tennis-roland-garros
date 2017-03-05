package com.app.tennis.listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.data.Pays;
import com.app.tennis.data.Tournoi;
import com.app.tennis.data.TypeQualification;

public class InitialisationListener implements ServletContextListener {
	
	private String NAME_PERSISTENCE = "tennis-db";
	private String NAME_DB = "/app-db.sql";
	
	List<Tournoi> listeTournois = null;
	List<TypeQualification> listeQualifications = null;
	List<NiveauArbitre> listeNiveauArbitre = null;
	List<Pays> listePays = null;
	
	public InitialisationListener() {

	}

	public void contextInitialized(ServletContextEvent e) { 
		
		ServletContext application = e.getServletContext();
		DAOFactory daoFactory;

		daoFactory = (DAOFactory) application.getAttribute("daoFactory");

		if (daoFactory==null){
			
			daoFactory = new DAOFactory( NAME_PERSISTENCE );
			daoFactory.initDatabase( NAME_DB );
			application.setAttribute("daoFactory",daoFactory);
		}
		
		try {
			
			DAO<Pays> paysDao                             =	daoFactory.getObjDAO(Pays.class);
			DAO<Tournoi>tournoiDao                        =	daoFactory.getObjDAO(Tournoi.class);
			DAO<NiveauArbitre>niveauArbitreDao            =	daoFactory.getObjDAO(NiveauArbitre.class);
			DAO<TypeQualification>typeQualificationDao    =	daoFactory.getObjDAO(TypeQualification.class);
			
			listeTournois                                 = tournoiDao.listAll();
			listePays                                     = paysDao.listAll();
			listeQualifications                           = typeQualificationDao.listAll();
			listeNiveauArbitre                            = niveauArbitreDao.listAll();
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		application.setAttribute("listePays",listePays);
		application.setAttribute("listeTournois",listeTournois);
		application.setAttribute("listeQualifications",listeQualifications);
		application.setAttribute("listeNiveauArbitre",listeNiveauArbitre);
		

	}

	public void contextDestroyed(ServletContextEvent e)  { 
		
		ServletContext application = e.getServletContext();
		DAOFactory daoFactory;

		daoFactory = (DAOFactory) application.getAttribute("daoFactory");

		if (daoFactory!=null){
			daoFactory.closeEntityManager();
		}

	}

}
