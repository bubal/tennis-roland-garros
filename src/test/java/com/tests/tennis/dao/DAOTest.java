package com.tests.tennis.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.AfterClass;
import org.junit.Before;
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

	private static String nameDB = "tennis-test-db";
	protected static DAOFactory daoFactory;

	protected static DAO<Acces> accesDao;
	protected static DAO<Pays> paysDao;
	protected static DAO<Tournoi> tournoiDao;
	protected static DAO<NiveauArbitre> niveauArbitreDao;
	protected static DAO<TypeQualification> typeQualificationDao;
	protected static DAO<Joueur> joueurDao;
	protected static DAO<Arbitre> arbitreDao;
	protected static DAO<Court> courtDao;
	protected static DAO<Match> matchDao;

	
	@BeforeClass
	public static void init() throws DAOConfigurationException, DAOException, Exception{

		daoFactory=new DAOFactory(nameDB);

		accesDao = daoFactory.getObjDAO(Acces.class);
		paysDao = daoFactory.getObjDAO(Pays.class);
		tournoiDao = daoFactory.getObjDAO(Tournoi.class);
		niveauArbitreDao = daoFactory.getObjDAO(NiveauArbitre.class);
		typeQualificationDao = daoFactory.getObjDAO(TypeQualification.class);
		joueurDao = daoFactory.getObjDAO(Joueur.class);
		arbitreDao = daoFactory.getObjDAO(Arbitre.class);
		courtDao = daoFactory.getObjDAO(Court.class);
		matchDao = daoFactory.getObjDAO(Match.class);

	}

	
	@Before
	public void initializeDatabase(){

		Session session = daoFactory.getEntityManager().unwrap(Session.class);
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				try {
					File script = new File(getClass().getResource("/h2.sql").getFile());
					RunScript.execute(connection, new FileReader(script));
				} catch (FileNotFoundException e) {
					throw new RuntimeException("could not initialize with script");
				}
			}
		});
	}

	
//	@AfterClass
//	public static void tearDown(){
//		daoFactory.closeEntityManager();
//	}

}
