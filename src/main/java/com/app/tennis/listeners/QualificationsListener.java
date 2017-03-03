package com.app.tennis.listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.TypeQualification;


public class QualificationsListener implements ServletContextListener {

   
    public QualificationsListener() {
    }

    public void contextDestroyed(ServletContextEvent e)  { 
    	
    	
    }

    public void contextInitialized(ServletContextEvent e)  { 
    	ServletContext application = e.getServletContext();
    	DAOFactory daoFactory;
    	
    	List<TypeQualification> listeQualifications = null;
    	
    	daoFactory = (DAOFactory) application.getAttribute("daoFactory");
    	
    	if (daoFactory==null){
    		daoFactory = new DAOFactory("tennis-db");
			application.setAttribute("daoFactory",daoFactory);
    	}
    	
    	try {
    		DAO<TypeQualification> qualificationDao= daoFactory.getObjDAO(TypeQualification.class);
			listeQualifications = qualificationDao.listAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	application.setAttribute("listeQualifications",listeQualifications);
    }
	
}
