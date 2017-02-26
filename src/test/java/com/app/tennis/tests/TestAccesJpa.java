package com.app.tennis.tests;



import java.lang.reflect.InvocationTargetException;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Acces;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class TestAccesJpa {

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
			DAO<Acces> objDao = daoFactory.getObjJPA(Acces.class);
			
			//DAO<Pays> objDaojdbc = daoFactory.getObjJDBC(Pays.class);

			System.out.println("1. Tous les Acces");
			System.out.println("");
			for (Acces obj : objDao.listAll()){
				System.out.println(obj.toString());
			}
			
			String login = "bubu";
			String password = "admin";
			
			Acces user = new Acces(login);
			try {
				user = objDao.find("login",login);
				user.setExist(true);
			} catch (Exception e) {
				System.out.println("Le login "+ login + " n'existe pas!");
			}
			
			if(user.isExist()){
				if (user.isAcces(password)){
						System.out.println("Bravo connecté!");
				} 
				else{
					System.out.println("Mot de passe érroné !");
				}
			} 	
				
			
			
//			for (Pays pays : objDaojdbc.listAll()){
//				System.out.println(pays.toString());
//			}
			
		}catch (DAOConfigurationException | DAOException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}

	}

}
