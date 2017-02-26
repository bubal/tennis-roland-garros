package com.app.tennis.dao;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.app.tennis.dao.jpa.ObjJPA;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class DAOFactory {

	private String url;
	private String username;
	private String password;
	private static EntityManager entityManager;
	
	
	DAOFactory( String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	
	
	public static DAOFactory getInstance() throws DAOConfigurationException {

		String driver; 
		String url;
		String username; 
		String password;

		ResourceBundle ressources = ResourceBundle.getBundle("paramDB") ; 

		if (ressources == null){
			throw new DAOConfigurationException( "Le fichier properties est introuvable." );
		} 
		try {
			driver = ressources.getString("driver"); 
			url = ressources.getString("url");
			username = ressources.getString("username"); 
			password = ressources.getString("password");

			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException( "Le driver est introuvable.", e );
		}
		
		if (entityManager==null){
			entityManager = Persistence.createEntityManagerFactory("tennispersistence").createEntityManager();
			if (entityManager==null){
				throw new DAOConfigurationException("Echec du chargement de la persistence");
			}
		}
		DAOFactory instance = new DAOFactory( url, username, password);
		return instance;
	}

	
	public Connection getConnection() throws DAOException{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(this.url,this.username,this.password);
		} catch (SQLException e) {
			throw new DAOException ("Echec de la connexion à la base de données !",e);
		}
		return connection;
	}

	
	public EntityManager getEntityManager() throws DAOException{
		return entityManager;
	}
	
	
	
	/* Accès aux DAO jdbc */	
	public <T> DAO<T> getObjJDBC(Class<T> typeClass) throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		String[] nameObj = typeClass.getName().split("\\.");
		
		String strClass = "com.cgi.rolandgarros.dao.jdbc."+nameObj[nameObj.length-1]+"DAO";
		
		Class<?> classeDAO = Class.forName (strClass);
		
		@SuppressWarnings("unchecked")
		DAO<T> objDAO = (DAO<T>) classeDAO.newInstance();
		Method method = classeDAO.getMethod("setDaoFactory", DAOFactory.class);
		method.invoke(objDAO, this);
		return objDAO;
	}
	
	
	/* Accès au DAO jpa */
	public <T> DAO<T> getObjJPA(Class<T> typeClass) throws DAOException{
		return new ObjJPA<T>(this,typeClass);
	}
	
}
