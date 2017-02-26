package com.app.tennis.tests;



import java.lang.reflect.InvocationTargetException;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Tournoi;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class TestTournoiJpa {

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
			DAO<Tournoi> objDao = daoFactory.getObjJPA(Tournoi.class);
			

			System.out.println("1. Tous les pays");
			System.out.println("");
			for (Tournoi obj : objDao.listAll()){
				System.out.println(obj.toString());
			}
			
		}catch (DAOConfigurationException | DAOException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}

	}

}
