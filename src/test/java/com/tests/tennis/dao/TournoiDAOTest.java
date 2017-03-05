package com.tests.tennis.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.app.tennis.data.Tournoi;

public class TournoiDAOTest extends DAOTest {

	@Test
	public void testCreate() throws Exception {

		Tournoi obj = new Tournoi("Nouveau Tournoi",4);

		Tournoi newObj = tournoiDao.create(obj);

		assertNotNull(newObj);
		assertEquals(obj.getNom(),newObj.getNom());
	}

	@Test
	public void testFindById() throws Exception {

		Tournoi obj = tournoiDao.findById(1);

		assertNotNull(obj);
		assertEquals("Simple Messieurs",obj.getNom());
	}

	@Test
	public void testUpdate() throws Exception {

		Tournoi obj = tournoiDao.findById(2);
		assertNotNull(obj);

		obj.setNom("Simple mixte");

		Tournoi newObj = tournoiDao.update(obj);

		assertNotNull(newObj);
		assertEquals(obj,newObj);
	}

	@Test
	public void testDeleteById() throws Exception {

		List<Tournoi> listBefore = tournoiDao.listAll();
		assertNotNull(listBefore);

		tournoiDao.deleteById(listBefore.size());

		List<Tournoi> listAfter = tournoiDao.listAll();
		assertNotNull(listAfter);

		assertEquals(listBefore.size(),listAfter.size()+1);
	}
}
