package com.app.tennis.listeners;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;


public class NiveauArbitreListener implements ServletContextListener {

    
    public NiveauArbitreListener() {
      
    }

    public void contextDestroyed(ServletContextEvent e)  { 
         
    }

    public void contextInitialized(ServletContextEvent e)  { 
    	
    	ServletContext application = e.getServletContext();
    	DAOFactory daoFactory;
    	List<NiveauArbitre> listeNiveauArbitre = null;
    	
    	daoFactory = (DAOFactory) application.getAttribute("daoFactory");
    	
    	if (daoFactory==null){
    		try {
				daoFactory = new DAOFactory();
				application.setAttribute("daoFactory",daoFactory);
			} catch (DAOConfigurationException e1) {
				e1.printStackTrace();
			}
    	}
    	
    	try {
    		DAO<NiveauArbitre> niveauDao = daoFactory.getObjDAO(NiveauArbitre.class);
			listeNiveauArbitre = niveauDao.listAll();
			
		} catch (DAOException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
    	
    	application.setAttribute("listeNiveauArbitre",listeNiveauArbitre);
    	
    }
	
}
