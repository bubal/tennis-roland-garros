package com.app.tennis.listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Pays;
import com.app.tennis.exceptions.DAOConfigurationException;



public class PaysListener implements ServletContextListener {

   
    public PaysListener() {
    	
    }

    public void contextInitialized(ServletContextEvent e)  { 
         
    	ServletContext application = e.getServletContext();
    	
    	DAOFactory daoFactory;
    	List<Pays> listePays = null;
    	
    	daoFactory = (DAOFactory) application.getAttribute("daoFactory");
    	
    	try {
    		
    		if (daoFactory==null){
        		throw new DAOConfigurationException("Erreur : la daoFactory n'a pas été initialisée");
        	}
    		
			DAO<Pays> paysDao = daoFactory.getObjDAO(Pays.class);
			listePays = paysDao.listAll();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	application.setAttribute("listePays",listePays);
    	
    }
    
    public void contextDestroyed(ServletContextEvent e)  { 
        
    }
	
}
