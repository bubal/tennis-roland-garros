package com.app.tennis.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.app.tennis.dao.AccesDAO;
import com.app.tennis.data.Acces;
import com.app.tennis.exceptions.DAOException;

public class AccesDAOImpl extends ObjDAOImpl<Acces> implements AccesDAO {

	public AccesDAOImpl(EntityManager connection) throws DAOException {
		super(connection, Acces.class);
	}

	@Override
	public Acces findByLogin(String login){
		TypedQuery<Acces> query;
		query = connection.createNamedQuery("Acces.findObj", Acces.class).setParameter("login", login);
		return query.getSingleResult();
	}
}