package com.tests.tennis.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.app.tennis.data.TypeQualification;

public class TypeQualificationDAOTest extends DAOTest {
	
	@Test
	public void testFindIntJpa() throws Exception {
		TypeQualification obj = typeQualificationDao.findById(1);
		assertEquals("Wildcards",obj.getNom());
	}

	@Test
	public void testListAllJpa() throws Exception {
		List<TypeQualification> listeObj = new ArrayList<TypeQualification>();
		listeObj = typeQualificationDao.listAll();
		assertTrue(listeObj.size()==3);
		assertEquals("Par classement ATP/WPA",listeObj.get(1).getNom());
	}

}
