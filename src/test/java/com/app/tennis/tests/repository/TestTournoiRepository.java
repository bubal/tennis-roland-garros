package com.app.tennis.tests.repository;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.app.tennis.data.Tournoi;
import com.app.tennis.services.TournoiService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-application-context.xml"})
@WebAppConfiguration
public class TestTournoiRepository {
	
	@Autowired
	private TournoiService serviceTournoi;

	@Test
	public void testCreate() throws Exception {

		Tournoi obj = new Tournoi("Nouveau Tournoi",4);

		Tournoi newObj = serviceTournoi.create(obj);

		assertNotNull(newObj);
		assertEquals(obj.getNom(),newObj.getNom());
	}

	@Test
	public void testFindById() throws Exception {

		Tournoi obj = serviceTournoi.findById(1);

		assertNotNull(obj);
		assertEquals("Simple Messieurs",obj.getNom());
	}

	@Test
	public void testUpdate() throws Exception {

		Tournoi obj = serviceTournoi.findById(2);
		assertNotNull(obj);

		obj.setNom("Simple mixte");

		Tournoi newObj = serviceTournoi.update(obj);

		assertNotNull(newObj);
		assertEquals(obj,newObj);
	}

	@Test
	public void testDeleteById() throws Exception {

		List<Tournoi> listBefore = serviceTournoi.listAll();
		assertNotNull(listBefore);

		serviceTournoi.deleteById(listBefore.size());

		List<Tournoi> listAfter = serviceTournoi.listAll();
		assertNotNull(listAfter);

		assertEquals(listBefore.size(),listAfter.size()+1);
	}
}
