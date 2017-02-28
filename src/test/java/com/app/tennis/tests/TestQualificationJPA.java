package com.app.tennis.tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.exceptions.DAOConfigurationException;
import com.app.tennis.exceptions.DAOException;

public class TestQualificationJPA {
	
	private static DAOFactory daoFactory;
	private static DAO<TypeQualification> objDaoJpa;

	@BeforeClass
	public static void initDAO() throws DAOConfigurationException, DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
			daoFactory = new DAOFactory();
			objDaoJpa = daoFactory.getObjJPA(TypeQualification.class);
		}
	
	@Test
	public void testFindIntJpa() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		TypeQualification obj = objDaoJpa.find(1);
		assertEquals("Wildcards",obj.getNom());
	}

	@Test
	public void testListAllJpa() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DAOException {
		List<TypeQualification> listeObj = new ArrayList<TypeQualification>();
		listeObj = objDaoJpa.listAll();
		assertTrue(listeObj.size()==3);
		assertEquals("Par classement ATP/WPA",listeObj.get(1).getNom());
	}

}
