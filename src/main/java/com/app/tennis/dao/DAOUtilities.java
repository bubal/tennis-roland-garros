package com.app.tennis.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOUtilities {

	private static EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;
	private String persistenceUnitName;
	
	public DAOUtilities (String persistenceUnitName){
		this.persistenceUnitName = persistenceUnitName;
	}
	
	public EntityManager getEntityManager(){
		if (entityManager==null){
			entityManagerFactory = Persistence.createEntityManagerFactory(this.persistenceUnitName);
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}
	
	public void closeEntityManager(){
		if (entityManager!=null){
			entityManager.clear();
			entityManager.close();
			entityManagerFactory.close();
		}
	}
}
