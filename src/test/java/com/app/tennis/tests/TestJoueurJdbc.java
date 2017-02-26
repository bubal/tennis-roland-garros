package com.app.tennis.tests;

import java.lang.reflect.InvocationTargetException;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Joueur;
import com.app.tennis.data.Pays;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class TestJoueurJdbc {

	public static void main(String[] args) {

		System.out.println("####################################");
		System.out.println("#### Application Gestion Tennis ####");
		System.out.println("####                            ####");
		System.out.println("#### Tests Unitaires DAO Joueur ####");
		System.out.println("####################################");
		System.out.println("");
		
		DAOFactory daoFactory;
		
		try {
			daoFactory = DAOFactory.getInstance();
			DAO<Joueur> joueurDao = daoFactory.getObjJDBC(Joueur.class);
			System.out.println("1. Tous les joueurs et joueuses");
			System.out.println("");
			for (Joueur joueur : joueurDao.listAll()){
				System.out.println(joueur.toString());
			}
			
			System.out.println("");
			System.out.println("####################################");
			System.out.println("");
			
			System.out.println("1. Ajout d'un joueur");
			System.out.println("");
			
			Joueur newJoueur = new Joueur();
			Pays pays = new Pays();
			pays.setId(2);
			pays.setNom("Allemagne");
			
			TypeQualification qualification = new TypeQualification();
			qualification.setId(1);
			qualification.setNom("Wildcards");
			
			newJoueur.setNom("Zverev");
			newJoueur.setPrenom("Mischa");
			newJoueur.setPays(pays);
			newJoueur.setQualification(qualification);
			newJoueur.setSexe("H");
			newJoueur.setClassement(2010);
			
			
			newJoueur = joueurDao.create(newJoueur);
			
			System.out.println("Nouveau joueur créé : ");
			System.out.println(newJoueur.toString());
			
			System.out.println("");
			System.out.println("####################################");
			System.out.println("");
			
			/* Modification d'un joueur */
			System.out.println("2. Modification d'un joueur");
			System.out.println("");
			newJoueur.setNom("Paire");
			newJoueur.setPrenom("Benoit");
			newJoueur.setClassement(5020);
			
			newJoueur = joueurDao.update(newJoueur);
			
			System.out.println("Joueur modifié : ");
			System.out.println(newJoueur.toString());

			
			System.out.println("");
			System.out.println("####################################");
			System.out.println("");
			
			/* Suppression d'un joueur */
			System.out.println("3. Suppression du joueur");
			System.out.println("");
			
			joueurDao.delete(newJoueur.getId());
			
			System.out.println("");
			System.out.println("####################################");
			System.out.println("");
			System.out.println("4. Nouveau listing :");
			System.out.println("");
			
			for (Joueur joueur : joueurDao.listAll()){
				System.out.println(joueur.toString());
			}
			
		} catch (DAOConfigurationException | DAOException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}


