package com.tests.tennis.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.app.tennis.data.NiveauArbitre;

public class NiveauArbitreDAOTest extends DAOTest {

	@Test
	public void testFindIntJpa() throws Exception {
		NiveauArbitre obj = niveauArbitreDao.findById(1);
		assertEquals("A1",obj.getNom());
	}

	@Test
	public void testListAllJpa() throws Exception {
		List<NiveauArbitre> listeObj = new ArrayList<NiveauArbitre>();
		listeObj = niveauArbitreDao.listAll();
		assertTrue(listeObj.size()==3);
		assertEquals("A2",listeObj.get(1).getNom());
	}

}
