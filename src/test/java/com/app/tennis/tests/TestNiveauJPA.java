package com.app.tennis.tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class TestNiveauJPA {
	
	private static DAOFactory daoFactory;
	private static DAO<NiveauArbitre> objDaoJpa;
	private static DAO<NiveauArbitre> objDaoJdbc;

	@BeforeClass
	public static void initDAO() throws DAOConfigurationException, DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
			daoFactory = DAOFactory.getInstance();
			objDaoJpa = daoFactory.getObjJPA(NiveauArbitre.class);
			objDaoJdbc = daoFactory.getObjJDBC(NiveauArbitre.class);
		}
	
	@Test
	public void testFindIntJpa() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		NiveauArbitre obj = objDaoJpa.find(1);
		assertEquals("A1",obj.getNom());
	}

	@Test
	public void testListAllJpa() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		List<NiveauArbitre> listeObj = new ArrayList<NiveauArbitre>();
		listeObj = objDaoJpa.listAll();
		assertTrue(listeObj.size()==3);
		assertEquals("A2",listeObj.get(1).getNom());
	}
	
	@Test
	public void testFindIntJdbc() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		NiveauArbitre obj = objDaoJdbc.find(1);
		assertEquals("A1",obj.getNom());
	}

	@Test
	public void testListAllJdbc() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		List<NiveauArbitre> listeObj = new ArrayList<NiveauArbitre>();
		listeObj = objDaoJdbc.listAll();
		assertTrue(listeObj.size()==3);
		assertEquals("A2",listeObj.get(1).getNom());
	}

}
