package com.app.tennis.tests;

import java.lang.reflect.InvocationTargetException;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Acces;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class TestAccesJdbc {

	public static void main(String[] args) {
		System.out.println("####################################");
		System.out.println("#### Application Gestion Tennis ####");
		System.out.println("####                            ####");
		System.out.println("#### Tests Unitaires DAO Acces  ####");
		System.out.println("####################################");
		System.out.println("");

		DAOFactory daoFactory;

		String login = "adm";
		Acces demandeAcces = new Acces(login);

		try {
			daoFactory = DAOFactory.getInstance();
			DAO<Acces> accesDao = daoFactory.getObjJDBC(Acces.class);
			demandeAcces = accesDao.find(demandeAcces.getId());
		} catch (DAOConfigurationException | DAOException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		if(demandeAcces.isExist()){

			String prompt_password = "admin";
			if (demandeAcces.isAcces(prompt_password)){
				System.out.println("Bravo " + demandeAcces.getLogin() + " ! vous êtes logué!!" );
			} 
			else
			{
				System.out.println("Mot de passe érroné pour le login " + demandeAcces.getLogin() + " !" );
			}
		} else
		{
			System.out.println("Le login "+ demandeAcces.getLogin() + " n'existe pas!" );
		}
	}
}
