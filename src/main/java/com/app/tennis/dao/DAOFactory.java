package com.app.tennis.dao;


import com.app.tennis.dao.impl.ObjDAOImpl;
import com.app.tennis.exceptions.DAOException;

public class DAOFactory extends DAOUtilities{

	public DAOFactory(String persistenceUnitName) {
		super(persistenceUnitName);
	}
	
	/* Accès aux DAO */	
	public <T> DAO<T> getObjDAO(Class<T> typeClass) throws DAOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		/* On construit le nom de la classe DAO à partir du nom de l'objet et on la charge */
		String strClass = ObjDAOImpl.class.getPackage().getName()+"."+typeClass.getSimpleName()+"DAOImpl";
		Class<?> classeDAO = Class.forName (strClass);
		
		/* On instancie la classe DAO */
		@SuppressWarnings("unchecked")
		DAO<T> objDAO = (DAO<T>) classeDAO.newInstance();
		return objDAO;
	}
	
}
