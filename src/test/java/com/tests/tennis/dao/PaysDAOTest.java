package com.tests.tennis.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.app.tennis.data.Pays;


public class PaysDAOTest extends DAOTest {

	@Test
	public void testCreate() throws Exception {

		Pays obj = new Pays("Pologne");

		Pays newObj = paysDao.create(obj);

		assertNotNull(newObj);
		assertEquals(obj.getNom(),newObj.getNom());
	}

	@Test
	public void testFindById() throws Exception {

		Pays obj = paysDao.findById(1);

		assertNotNull(obj);
		assertEquals("Angleterre",obj.getNom());
	}

	@Test
	public void testUpdate() throws Exception {

		Pays obj = paysDao.findById(2);
		assertNotNull(obj);

		obj.setNom("Royaume-Unis");

		Pays newObj = paysDao.update(obj);

		assertNotNull(newObj);
		assertEquals(obj,newObj);
	}

	@Test
	public void testDeleteById() throws Exception {

		List<Pays> listBefore = paysDao.listAll();
		assertNotNull(listBefore);

		paysDao.deleteById(listBefore.size());

		List<Pays> listAfter = paysDao.listAll();
		assertNotNull(listAfter);

		assertEquals(listBefore.size(),listAfter.size()+1);
	}
}
