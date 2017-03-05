package com.tests.tennis.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.app.tennis.data.TypeQualification;

public class TypeQualificationDAOTest extends DAOTest {

	@Test
	public void testCreate() throws Exception {

		TypeQualification obj = new TypeQualification("Qualification Test");

		TypeQualification newObj = typeQualificationDao.create(obj);

		assertNotNull(newObj);
		assertEquals(obj.getNom(),newObj.getNom());
	}

	@Test
	public void testFindById() throws Exception {

		TypeQualification obj = typeQualificationDao.findById(1);

		assertNotNull(obj);
		assertEquals("Wildcards",obj.getNom());
	}

	@Test
	public void testUpdate() throws Exception {

		TypeQualification obj = typeQualificationDao.findById(2);
		assertNotNull(obj);

		obj.setNom("Par jet de dés");

		TypeQualification newObj = typeQualificationDao.update(obj);

		assertNotNull(newObj);
		assertEquals(obj,newObj);
	}

	@Test
	public void testDeleteById() throws Exception {

		List<TypeQualification> listBefore = typeQualificationDao.listAll();
		assertNotNull(listBefore);

		typeQualificationDao.deleteById(listBefore.size());

		List<TypeQualification> listAfter = typeQualificationDao.listAll();
		assertNotNull(listAfter);

		assertEquals(listBefore.size(),listAfter.size()+1);
	}
}
