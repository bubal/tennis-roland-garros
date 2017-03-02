package com.app.tennis.dao;


import java.lang.reflect.InvocationTargetException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.app.tennis.dao.impl.ObjDAOImpl;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class DAOFactory {

	/* Singleton de connexion */
	private static EntityManager entityManager;
	
	public DAOFactory() throws DAOConfigurationException{
		
		/* On test si le singleton est déjà instancié ou non */
		if (entityManager==null){
			entityManager = Persistence.createEntityManagerFactory("tennis-test-db").createEntityManager();
			if (entityManager==null){
				throw new DAOConfigurationException("Echec du chargement de la persistence");
			}
		}
	}
	
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	/* Accès aux DAO */	
	public <T> DAO<T> getObjDAO(Class<T> typeClass) throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		/* On construit le nom de la classe DAO à partir du nom de l'objet et on la charge */
		String strClass = ObjDAOImpl.class.getPackage().getName()+"."+typeClass.getSimpleName()+"DAOImpl";
		Class<?> classeDAO = Class.forName (strClass);
		
		/* On instancie la classe DAO */
		@SuppressWarnings("unchecked")
		DAO<T> objDAO = (DAO<T>) classeDAO.getDeclaredConstructor(EntityManager.class).newInstance(entityManager);
		return objDAO;
	}
	
}
