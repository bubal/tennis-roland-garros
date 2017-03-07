package com.app.tennis.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOUtilities {

	public  static EntityManager        entityManager;
	private static EntityManagerFactory entityManagerFactory;
	private static String               persistenceUnitName;
	
	public DAOUtilities (String persistenceUnitName){
		
		DAOUtilities.persistenceUnitName = persistenceUnitName;
	}
	
	
	public static EntityManager getEntityManager(){
		
		if ( entityManager == null || !entityManager.isOpen() ){
			entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}
	
	
	public void closeEntityManager(){
		if (entityManager!=null && entityManager.isOpen()){
			entityManager.clear();
			entityManager.close();
			entityManagerFactory.close();
		}
	}
	
}
