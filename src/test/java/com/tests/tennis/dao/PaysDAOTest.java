package com.tests.tennis.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.app.tennis.data.Pays;

public class PaysDAOTest extends DAOTest {
	
	
	@Test
	public void testFindIntJpa() throws Exception {
		Pays obj = paysDao.findById(1);
		assertEquals("Angleterre",obj.getNom());
	}

	@Test
	public void testListAllJpa() throws Exception {
		List<Pays> listeObj = new ArrayList<Pays>();
		listeObj = paysDao.listAll();
		assertTrue(listeObj.size()==17);
		assertEquals("Angleterre",listeObj.get(1).getNom());
	}

}
