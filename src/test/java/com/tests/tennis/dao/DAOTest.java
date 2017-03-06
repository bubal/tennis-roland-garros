package com.tests.tennis.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.app.tennis.dao.AccesDAO;
import com.app.tennis.dao.ArbitreDAO;
import com.app.tennis.dao.CourtDAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.dao.JoueurDAO;
import com.app.tennis.dao.MatchDAO;
import com.app.tennis.dao.NiveauArbitreDAO;
import com.app.tennis.dao.PaysDAO;
import com.app.tennis.dao.TournoiDAO;
import com.app.tennis.dao.TypeQualificationDAO;
import com.app.tennis.data.Acces;
import com.app.tennis.data.Arbitre;
import com.app.tennis.data.Court;
import com.app.tennis.data.Joueur;
import com.app.tennis.data.Match;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.data.Pays;
import com.app.tennis.data.Tournoi;
import com.app.tennis.data.TypeQualification;

public class DAOTest {

	private static String NAME_PERSISTENCE = "tennis-test-db";
	private static String NAME_DB = "/test-db.sql";
	
	protected static DAOFactory daoFactory; 

	protected static AccesDAO              accesDao;
	protected static PaysDAO               paysDao;
	protected static TournoiDAO            tournoiDao;
	protected static NiveauArbitreDAO      niveauArbitreDao;
	protected static TypeQualificationDAO  typeQualificationDao;
	protected static JoueurDAO             joueurDao;
	protected static ArbitreDAO            arbitreDao;
	protected static CourtDAO              courtDao;
	protected static MatchDAO              matchDao;

	
	@BeforeClass
	public static void init() throws Exception{

		daoFactory = new DAOFactory( NAME_PERSISTENCE );

		daoFactory.initDatabase( NAME_DB );
		
		
		accesDao                =	(AccesDAO) daoFactory.getObjDAO(Acces.class);
		paysDao                 =	(PaysDAO) daoFactory.getObjDAO(Pays.class);
		tournoiDao              =	(TournoiDAO) daoFactory.getObjDAO(Tournoi.class);
		niveauArbitreDao        =	(NiveauArbitreDAO) daoFactory.getObjDAO(NiveauArbitre.class);
		typeQualificationDao    =	(TypeQualificationDAO) daoFactory.getObjDAO(TypeQualification.class);
		joueurDao               =	(JoueurDAO) daoFactory.getObjDAO(Joueur.class);
		arbitreDao              =	(ArbitreDAO) daoFactory.getObjDAO(Arbitre.class);
		courtDao                =	(CourtDAO) daoFactory.getObjDAO(Court.class);
		matchDao                =	(MatchDAO) daoFactory.getObjDAO(Match.class);

	}
	
	@AfterClass
	public static void tearDown(){
		daoFactory.closeEntityManager();
	}

}
