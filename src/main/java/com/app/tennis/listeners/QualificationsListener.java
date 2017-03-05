package com.app.tennis.listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.exceptions.DAOConfigurationException;


public class QualificationsListener implements ServletContextListener {


	public QualificationsListener() {

	}

	public void contextInitialized(ServletContextEvent e)  { 
		ServletContext application = e.getServletContext();
		DAOFactory daoFactory;

		List<TypeQualification> listeQualifications = null;

		daoFactory = (DAOFactory) application.getAttribute("daoFactory");

		try {
			if (daoFactory==null){
				throw new DAOConfigurationException("Erreur : la daoFactory n'a pas été initialisée");
			}

			DAO<TypeQualification> qualificationDao= daoFactory.getObjDAO(TypeQualification.class);
			listeQualifications = qualificationDao.listAll();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		application.setAttribute("listeQualifications",listeQualifications);
	}

	public void contextDestroyed(ServletContextEvent e)  { 

	}
}
