package com.tests.tennis.dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import com.app.tennis.data.Acces;
import com.app.tennis.exceptions.DAOException;

public class AccesDAOTest extends DAOTest {
	
	@Test
	public void testCreate() throws Exception {

		Acces obj = new Acces("newUser");
		obj.setPassword("newpass");

		Acces newObj = accesDao.create(obj);

		assertNotNull(newObj);
		assertEquals(obj.getLogin(),newObj.getLogin());
	}

	@Test
	public void testFindById() throws Exception {

		Acces obj = accesDao.findById(1);

		assertNotNull(obj);
		assertEquals("admin",obj.getLogin());
	}

	@Test
	public void testUpdate() throws Exception {

		Acces obj = accesDao.findById(2);
		assertNotNull(obj);

		obj.setLogin("orga");

		Acces newObj = accesDao.update(obj);

		assertNotNull(newObj);
		assertEquals(obj,newObj);
	}

	@Test
	public void testDeleteById() throws Exception {

		List<Acces> listBefore = accesDao.listAll();
		assertNotNull(listBefore);

		accesDao.deleteById(listBefore.size());

		List<Acces> listAfter = accesDao.listAll();
		assertNotNull(listAfter);

		assertEquals(listBefore.size(),listAfter.size()+1);
	}
	
	@Test
	public void testAccesLoginGoodPassBad() throws DAOException{
		
		Acces user = accesDao.findByLogin("admin");
		assertTrue(user.isExist());
		assertFalse(user.isAcces("wrong"));
	}
	
	@Test
	public void testAccesLoginGoodPassGood() throws DAOException{
		
		Acces user = accesDao.findByLogin("admin");
		assertTrue(user.isExist());
		assertTrue(user.isAcces("admin"));
	}
	
	@Test(expected=DAOException.class)
	public void testAccesLoginBad() throws DAOException{
		accesDao.findByLogin("inconnu");
	}
	
	
	
	
	
	
	
	
	
	
	

//	public static void main(String[] args) {
//		
//		System.out.println("####################################");
//		System.out.println("#### Application Gestion Tennis ####");
//		System.out.println("####                            ####");
//		System.out.println("#### Tests Unitaires DAO Match ####");
//		System.out.println("####################################");
//		System.out.println("");
//		
//		DAOFactory daoFactory;
//		
//		try {
//			
//			daoFactory=new DAOFactory("tennis-test-db");
//			
//			AccesDAO objDao = (AccesDAO) daoFactory.getObjDAO(Acces.class);
//			
//
//			System.out.println("1. Tous les Acces");
//			System.out.println("");
//			for (Acces obj : objDao.listAll()){
//				System.out.println(obj.toString());
//			}
//			
//			String login = "admin";
//			String password = "admin";
//			
//			Acces user = new Acces(login);
//			try {
//				user = objDao.findByLogin(login);
//				user.setExist(true);
//			} catch (Exception e) {
//				System.out.println("Le login "+ login + " n'existe pas!");
//			}
//			
//			if(user.isExist()){
//				if (user.isAcces(password)){
//						System.out.println("Bravo connecté!");
//				} 
//				else{
//					System.out.println("Mot de passe érroné !");
//				}
//			} 	
//				
//			
//			
////			for (Pays pays : objDaojdbc.listAll()){
////				System.out.println(pays.toString());
////			}
//			
//		}catch ( Exception e) {
//				e.printStackTrace();
//			}
//
//	}

}
