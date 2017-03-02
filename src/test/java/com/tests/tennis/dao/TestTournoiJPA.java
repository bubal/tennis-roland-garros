package com.tests.tennis.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.app.tennis.data.Tournoi;

public class TestTournoiJPA extends DAOTest {
	
	@Test
	public void testFindIntJpa() throws Exception {
		Tournoi obj = tournoiDao.findById(1);
		assertEquals("Simple Messieurs",obj.getNom());
	}

	@Test
	public void testListAllJpa() throws Exception {
		List<Tournoi> listeObj = new ArrayList<Tournoi>();
		listeObj = tournoiDao.listAll();
		assertTrue(listeObj.size()==6);
		assertEquals("Simple Dames",listeObj.get(1).getNom());
	}

}
