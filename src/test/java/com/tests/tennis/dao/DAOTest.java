package com.tests.tennis.dao;

import org.junit.BeforeClass;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Acces;
import com.app.tennis.data.Arbitre;
import com.app.tennis.data.Court;
import com.app.tennis.data.Joueur;
import com.app.tennis.data.Match;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.data.Pays;
import com.app.tennis.data.Tournoi;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class DAOTest {

	private static String persistenceUnitName = "tennis-test-db";
	private static String nameDB = "test-db";
	
	protected static DAOFactory daoFactory;

	protected static DAO<Acces> 			accesDao;
	protected static DAO<Pays>				paysDao;
	protected static DAO<Tournoi>			tournoiDao;
	protected static DAO<NiveauArbitre>		niveauArbitreDao;
	protected static DAO<TypeQualification> typeQualificationDao;
	protected static DAO<Joueur> 			joueurDao;
	protected static DAO<Arbitre>			arbitreDao;
	protected static DAO<Court>				courtDao;
	protected static DAO<Match>				matchDao;

	
	@BeforeClass
	public static void init() throws DAOConfigurationException, DAOException, Exception{

		daoFactory = new DAOFactory(persistenceUnitName);

		daoFactory.initDatabase( "/" + nameDB + ".sql" );
		
		accesDao 				=	daoFactory.getObjDAO(Acces.class);
		paysDao 				=	daoFactory.getObjDAO(Pays.class);
		tournoiDao 				=	daoFactory.getObjDAO(Tournoi.class);
		niveauArbitreDao 		=	daoFactory.getObjDAO(NiveauArbitre.class);
		typeQualificationDao 	=	daoFactory.getObjDAO(TypeQualification.class);
		joueurDao 				=	daoFactory.getObjDAO(Joueur.class);
		arbitreDao				=	daoFactory.getObjDAO(Arbitre.class);
		courtDao 				=	daoFactory.getObjDAO(Court.class);
		matchDao 				=	daoFactory.getObjDAO(Match.class);

	}

	
//	@AfterClass
//	public static void tearDown(){
//		daoFactory.closeEntityManager();
//	}

}
