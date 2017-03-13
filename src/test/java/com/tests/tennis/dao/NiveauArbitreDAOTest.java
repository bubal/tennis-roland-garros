package com.tests.tennis.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.services.NiveauArbitreService;

public class NiveauArbitreDAOTest extends GenericTest {
	
	@Autowired
	private NiveauArbitreService serviceNiveauArbitre;
	

	@Test
	public void testCreate() throws Exception {

		NiveauArbitre obj = new NiveauArbitre("Ax","Indéterminé");

		NiveauArbitre newObj = serviceNiveauArbitre.create(obj);

		assertNotNull(newObj);
		assertEquals(obj.getNom(),newObj.getNom());
	}

	@Test
	public void testFindById() throws Exception {

		NiveauArbitre obj = serviceNiveauArbitre.findById(1);

		assertNotNull(obj);
		assertEquals("A1",obj.getNom());
	}

	@Test
	public void testUpdate() throws Exception {

		NiveauArbitre obj = serviceNiveauArbitre.findById(2);
		assertNotNull(obj);

		obj.setNom("A7");

		NiveauArbitre newObj = serviceNiveauArbitre.update(obj);

		assertNotNull(newObj);
		assertEquals(obj,newObj);
	}

	@Test
	public void testDeleteById() throws Exception {

		List<NiveauArbitre> listBefore = serviceNiveauArbitre.listAll();
		assertNotNull(listBefore);

		serviceNiveauArbitre.deleteById(listBefore.size());

		List<NiveauArbitre> listAfter = serviceNiveauArbitre.listAll();
		assertNotNull(listAfter);

		assertEquals(listBefore.size(),listAfter.size()+1);
	}
}
