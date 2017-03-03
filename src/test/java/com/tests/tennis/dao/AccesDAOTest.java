package com.tests.tennis.dao;

import com.app.tennis.dao.AccesDAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Acces;

public class AccesDAOTest {

	public static void main(String[] args) {
		
		System.out.println("####################################");
		System.out.println("#### Application Gestion Tennis ####");
		System.out.println("####                            ####");
		System.out.println("#### Tests Unitaires DAO Match ####");
		System.out.println("####################################");
		System.out.println("");
		
		DAOFactory daoFactory;
		
		try {
			
			daoFactory=new DAOFactory("tennis-test-db");
			
			AccesDAO objDao = (AccesDAO) daoFactory.getObjDAO(Acces.class);
			

			System.out.println("1. Tous les Acces");
			System.out.println("");
			for (Acces obj : objDao.listAll()){
				System.out.println(obj.toString());
			}
			
			String login = "admin";
			String password = "admin";
			
			Acces user = new Acces(login);
			try {
				user = objDao.findByLogin(login);
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
			
		}catch ( Exception e) {
				e.printStackTrace();
			}

	}

}
