package com.app.tennis.tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Tournoi;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class TestTournoiJPA {
	
	private static DAOFactory daoFactory;
	private static DAO<Tournoi> objDaoJpa;

	@BeforeClass
	public static void initDAO() throws DAOConfigurationException, DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
			daoFactory = new DAOFactory();
			objDaoJpa = daoFactory.getObjDAO(Tournoi.class);
		}
	
	@Test
	public void testFindIntJpa() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		Tournoi obj = objDaoJpa.find(1);
		assertEquals("Simple Messieurs",obj.getNom());
	}

	@Test
	public void testListAllJpa() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		List<Tournoi> listeObj = new ArrayList<Tournoi>();
		listeObj = objDaoJpa.listAll();
		assertTrue(listeObj.size()==6);
		assertEquals("Simple Dames",listeObj.get(1).getNom());
	}

}
