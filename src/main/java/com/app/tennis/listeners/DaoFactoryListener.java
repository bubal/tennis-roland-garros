package com.app.tennis.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.app.tennis.dao.DAOFactory;


public class DaoFactoryListener implements ServletContextListener {

	private String NAME_PERSISTENCE = "tennis-db";
	private String NAME_DB = "/app-db.sql";

	public DaoFactoryListener() {

	}

	public void contextInitialized(ServletContextEvent e)  { 

		ServletContext application = e.getServletContext();
		DAOFactory daoFactory;

		daoFactory = (DAOFactory) application.getAttribute("daoFactory");

		if (daoFactory==null){
			daoFactory = new DAOFactory( NAME_PERSISTENCE );
			daoFactory.initDatabase( NAME_DB );
			application.setAttribute("daoFactory",daoFactory);
		}
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
