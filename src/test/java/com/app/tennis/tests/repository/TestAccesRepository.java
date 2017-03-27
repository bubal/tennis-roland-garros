package com.app.tennis.tests.repository;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.app.tennis.data.Acces;
import com.app.tennis.services.AccesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-application-context.xml"})
@WebAppConfiguration
public class TestAccesRepository {
	
	@Autowired 
	private AccesService serviceAcces;
	
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
		obj.setPassword("passtest");

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

}
