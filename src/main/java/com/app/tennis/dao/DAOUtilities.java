package com.app.tennis.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

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
	
	
	public void initDatabase(String nameDB){

		Session session = DAOUtilities.getEntityManager().unwrap(Session.class);
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				try {
					File script = new File(getClass().getResource(nameDB).getFile());
					RunScript.execute(connection, new FileReader(script));
				} catch (FileNotFoundException e) {
					throw new RuntimeException("Impossible de charger le script d'initialisation de la base de données");
				}
			}
		});
	}
	
	public void closeEntityManager(){
		if (entityManager!=null && entityManager.isOpen()){
			entityManager.clear();
			entityManager.close();
			entityManagerFactory.close();
		}
	}
	
}
