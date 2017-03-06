package com.app.tennis.dao.impl;

import javax.persistence.TypedQuery;

import com.app.tennis.dao.AccesDAO;
import com.app.tennis.data.Acces;
import com.app.tennis.exceptions.DAOException;

public class AccesDAOImpl extends ObjDAOImpl<Acces> implements AccesDAO {

	@Override
	public Acces findByLogin(String login) throws DAOException{
		
		TypedQuery<Acces> query;
		
		query = connection.createNamedQuery("Acces.findObj", Acces.class).setParameter("login", login);
			
		Acces user = new Acces(login);
		try {
			user = query.getSingleResult();
			user.setExist(true);
		} catch (Exception e) {
			throw new DAOException("Le login " + login +" n'existe pas !");
		}
		
		return user;
	}

	@Override
	Class<Acces> getTypeClass() {
		return Acces.class;
	}
}