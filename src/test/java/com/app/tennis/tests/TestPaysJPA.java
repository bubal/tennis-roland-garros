package com.app.tennis.tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Pays;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class TestPaysJPA {
	
	private static DAOFactory daoFactory;
	private static DAO<Pays> objDaoJpa;
	

	@BeforeClass
	public static void initDAO() throws DAOConfigurationException, DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
			daoFactory = new DAOFactory();
			objDaoJpa = daoFactory.getObjJPA(Pays.class);
		}
	
	@Test
	public void testFindIntJpa() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		Pays obj = objDaoJpa.find(1);
		assertEquals("Angleterre",obj.getNom());
	}

	@Test
	public void testListAllJpa() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		List<Pays> listeObj = new ArrayList<Pays>();
		listeObj = objDaoJpa.listAll();
		assertTrue(listeObj.size()==17);
		assertEquals("Angleterre",listeObj.get(1).getNom());
	}

}
