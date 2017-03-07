package com.tests.tennis.dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import com.app.tennis.data.Acces;

public class AccesDAOTest extends DAOTest {
	
	@Test
	public void testCreate() throws Exception {

		Acces obj = new Acces("newUser");
		obj.setPassword("newpass");

		Acces newObj = serviceAcces.create(obj);

		assertNotNull(newObj);
		assertEquals(obj.getLogin(),newObj.getLogin());
	}

	@Test
	public void testFindById() throws Exception {

		Acces obj = serviceAcces.findById(1);

		assertNotNull(obj);
		assertEquals("admin",obj.getLogin());
	}

	@Test
	public void testUpdate() throws Exception {

		Acces obj = serviceAcces.findById(2);
		assertNotNull(obj);

		obj.setLogin("orga");

		Acces newObj = serviceAcces.update(obj);

		assertNotNull(newObj);
		assertEquals(obj,newObj);
	}

	@Test
	public void testDeleteById() throws Exception {

		List<Acces> listBefore = serviceAcces.listAll();
		assertNotNull(listBefore);

		serviceAcces.deleteById(listBefore.size());

		List<Acces> listAfter = serviceAcces.listAll();
		assertNotNull(listAfter);

		assertEquals(listBefore.size(),listAfter.size()+1);
	}
	
	@Test
	public void testAccesLoginNotFound() {
		
		assertFalse(serviceAcces.findByLogin("inconnu").isExist());
	}
	
	@Test
	public void testAccesLoginGoodPassBad() {
		
		Acces user = serviceAcces.grantedAcces("admin", "wrong");
		assertTrue(user.isExist());
		assertFalse(user.isAcces());
	}
	
	@Test
	public void testAccesLoginGoodPassGood() {
		
		Acces user = serviceAcces.grantedAcces("admin", "admin");
		assertTrue(user.isExist());
		assertTrue(user.isAcces());
	}	

}
