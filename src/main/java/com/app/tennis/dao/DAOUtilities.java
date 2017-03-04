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

	private static EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;
	private String persistenceUnitName;
	
	public DAOUtilities (String persistenceUnitName){
		this.persistenceUnitName = persistenceUnitName;
	}
	
	public EntityManager getEntityManager(){
		if (entityManager == null){
			entityManagerFactory = Persistence.createEntityManagerFactory(this.persistenceUnitName);
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}
	
	public void initDatabase(String nameDB){

		Session session = this.getEntityManager().unwrap(Session.class);
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				try {
					File script = new File(getClass().getResource(nameDB).getFile());
					RunScript.execute(connection, new FileReader(script));
				} catch (FileNotFoundException e) {
					throw new RuntimeException("could not initialize with script");
				}
			}
		});
	}
	
	public void closeEntityManager(){
		if (entityManager!=null){
			entityManager.clear();
			entityManager.close();
			entityManagerFactory.close();
		}
	}
}
