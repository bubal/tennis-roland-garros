package com.app.tennis.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.app.tennis.dao.AccesDAO;
import com.app.tennis.data.Acces;

public class AccesDAOImpl extends ObjDAOImpl<Acces> implements AccesDAO {

	@Override
	public Acces findByLogin(String login) {
		
		TypedQuery<Acces> query = connection.createNamedQuery("Acces.findObj", Acces.class).setParameter("login", login);
		Acces user = new Acces(login);
		try {
			user = query.getSingleResult();
			user.setExist(true);
		} catch (NoResultException e) {}
		
		return user;
	}

	@Override
	Class<Acces> getTypeClass() {
		return Acces.class;
	}
}