package com.app.tennis.listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Tournoi;
import com.app.tennis.exceptions.DAOConfigurationException;


public class TournoisListener implements ServletContextListener {

    
    public TournoisListener() {
    	
    }

    public void contextInitialized(ServletContextEvent e)  { 
    	ServletContext application = e.getServletContext();
    	DAOFactory daoFactory;
    	
    	List<Tournoi> listeTournois = null;
    	
    	daoFactory = (DAOFactory) application.getAttribute("daoFactory");
    	
    	try {
    		
    		if (daoFactory==null){
				throw new DAOConfigurationException("Erreur : la daoFactory n'a pas été initialisée");
			}
    		
    		DAO<Tournoi> tournoiDao= daoFactory.getObjDAO(Tournoi.class);
    		listeTournois = tournoiDao.listAll();
    		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	application.setAttribute("listeTournois",listeTournois);
    }
    
    public void contextDestroyed(ServletContextEvent e)  { 
    	
    }
	
}
