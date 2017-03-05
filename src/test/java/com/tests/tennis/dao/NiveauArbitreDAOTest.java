package com.tests.tennis.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.app.tennis.data.NiveauArbitre;

public class NiveauArbitreDAOTest extends DAOTest {

	@Test
	public void testCreate() throws Exception {

		NiveauArbitre obj = new NiveauArbitre("Ax","Indéterminé");

		NiveauArbitre newObj = niveauArbitreDao.create(obj);

		assertNotNull(newObj);
		assertEquals(obj.getNom(),newObj.getNom());
	}

	@Test
	public void testFindById() throws Exception {

		NiveauArbitre obj = niveauArbitreDao.findById(1);

		assertNotNull(obj);
		assertEquals("A1",obj.getNom());
	}

	@Test
	public void testUpdate() throws Exception {

		NiveauArbitre obj = niveauArbitreDao.findById(2);
		assertNotNull(obj);

		obj.setNom("A7");

		NiveauArbitre newObj = niveauArbitreDao.update(obj);

		assertNotNull(newObj);
		assertEquals(obj,newObj);
	}

	@Test
	public void testDeleteById() throws Exception {

		List<NiveauArbitre> listBefore = niveauArbitreDao.listAll();
		assertNotNull(listBefore);

		niveauArbitreDao.deleteById(listBefore.size());

		List<NiveauArbitre> listAfter = niveauArbitreDao.listAll();
		assertNotNull(listAfter);

		assertEquals(listBefore.size(),listAfter.size()+1);
	}
}
