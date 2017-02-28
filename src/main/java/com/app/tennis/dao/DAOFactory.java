package com.app.tennis.dao;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.app.tennis.dao.impl.ObjDAO;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class DAOFactory {

	private static EntityManager entityManager;
	
	public DAOFactory() throws DAOConfigurationException{
		
		if (entityManager==null){
			entityManager = Persistence.createEntityManagerFactory("tennispersistence").createEntityManager();
			if (entityManager==null){
				throw new DAOConfigurationException("Echec du chargement de la persistence");
			}
		}
	}
	
	/* Accès aux DAO */
	public <T> DAO<T> getObjDAO(Class<T> typeClass) throws DAOException{
		return new ObjDAO<T>(entityManager,typeClass);
	}
	
}
