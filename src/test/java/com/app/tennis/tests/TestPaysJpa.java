package com.app.tennis.tests;



import java.lang.reflect.InvocationTargetException;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Pays;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class TestPaysJpa {

	public static void main(String[] args) {
		
		System.out.println("####################################");
		System.out.println("#### Application Gestion Tennis ####");
		System.out.println("####                            ####");
		System.out.println("#### Tests Unitaires DAO Match ####");
		System.out.println("####################################");
		System.out.println("");
		
		DAOFactory daoFactory;
		
		try {
			daoFactory = DAOFactory.getInstance();
			//DAO<Pays> objDao = daoFactory.getObjJPA(Pays.class);
			
			DAO<Pays> objDaojdbc = daoFactory.getObjJDBC(Pays.class);

			System.out.println("1. Tous les pays");
			System.out.println("");
//			for (Pays pays : objDao.listAll()){
//				System.out.println(pays.toString());
//			}
			
			for (Pays pays : objDaojdbc.listAll()){
				System.out.println(pays.toString());
			}
			
		}catch (DAOConfigurationException | DAOException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}

	}

}
