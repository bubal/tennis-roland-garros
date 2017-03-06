package com.tests.tennis.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.app.tennis.dao.DAOUtilities;
import com.app.tennis.services.AccesService;
import com.app.tennis.services.ArbitreService;
import com.app.tennis.services.CourtService;
import com.app.tennis.services.JoueurService;
import com.app.tennis.services.MatchService;
import com.app.tennis.services.NiveauArbitreService;
import com.app.tennis.services.PaysService;
import com.app.tennis.services.TournoiService;
import com.app.tennis.services.TypeQualificationService;
import com.app.tennis.services.impl.AccesServiceImpl;
import com.app.tennis.services.impl.ArbitreServiceImpl;
import com.app.tennis.services.impl.CourtServiceImpl;
import com.app.tennis.services.impl.JoueurServiceImpl;
import com.app.tennis.services.impl.MatchServiceImpl;
import com.app.tennis.services.impl.NiveauArbitreServiceImpl;
import com.app.tennis.services.impl.PaysServiceImpl;
import com.app.tennis.services.impl.TournoiServiceImpl;
import com.app.tennis.services.impl.TypeQualificationServiceImpl;

public class DAOTest {

	private static String NAME_PERSISTENCE = "tennis-test-db";
	private static String NAME_DB = "/test-db.sql";
	
	protected static DAOUtilities daoUtilities; 

	protected static AccesService              serviceAcces;
	protected static PaysService               servicePays;
	protected static TournoiService            serviceTournoi;
	protected static NiveauArbitreService      serviceNiveauArbitre;
	protected static TypeQualificationService  serviceTypeQualification;
	protected static JoueurService             serviceJoueur;
	protected static ArbitreService            serviceArbitre;
	protected static CourtService              serviceCourt;
	protected static MatchService              serviceMatch;

	
	@BeforeClass
	public static void init() throws Exception{

		daoUtilities = new DAOUtilities( NAME_PERSISTENCE );

		daoUtilities.initDatabase( NAME_DB );
		
		
		serviceAcces                =	new AccesServiceImpl();
		servicePays                 =	new PaysServiceImpl();
		serviceTournoi              =	new TournoiServiceImpl();
		serviceNiveauArbitre        =	new NiveauArbitreServiceImpl();
		serviceTypeQualification    =	new TypeQualificationServiceImpl();
		serviceJoueur               =	new JoueurServiceImpl();
		serviceArbitre              =	new ArbitreServiceImpl();
		serviceCourt                =	new CourtServiceImpl();
		serviceMatch                =	new MatchServiceImpl();

	}
	
	@AfterClass
	public static void tearDown(){
		daoUtilities.closeEntityManager();
	}

}
