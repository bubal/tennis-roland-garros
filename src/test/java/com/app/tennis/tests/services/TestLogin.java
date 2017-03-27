package com.app.tennis.tests.services;

import static org.junit.Assert.*;

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
public class TestLogin {
	
	@Autowired 
	private AccesService serviceAcces;
	
	
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
