package com.app.tennis.listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.exceptions.DAOConfigurationException;


public class NiveauArbitreListener implements ServletContextListener {

    
    public NiveauArbitreListener() {
    	
    }

    public void contextInitialized(ServletContextEvent e){ 
    	
    	ServletContext application = e.getServletContext();
    	DAOFactory daoFactory;
    	List<NiveauArbitre> listeNiveauArbitre = null;
    	
    	daoFactory = (DAOFactory) application.getAttribute("daoFactory");
    	
    	try {
    		
    		if (daoFactory==null){
        		throw new DAOConfigurationException("Erreur : la daoFactory n'a pas été initialisée");
        	}
    		
    		DAO<NiveauArbitre> niveauDao = daoFactory.getObjDAO(NiveauArbitre.class);
			listeNiveauArbitre = niveauDao.listAll();
			
		} catch ( Exception e1 ) {
			e1.printStackTrace();
		}
    	
    	application.setAttribute("listeNiveauArbitre",listeNiveauArbitre);
    	
    }
    
    public void contextDestroyed(ServletContextEvent e)  {
    	
    }
	
}
