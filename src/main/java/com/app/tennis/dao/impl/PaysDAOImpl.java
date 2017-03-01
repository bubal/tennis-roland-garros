package com.app.tennis.dao.impl;

import javax.persistence.EntityManager;

import com.app.tennis.dao.PaysDAO;
import com.app.tennis.data.Pays;
import com.app.tennis.exceptions.DAOException;

public class PaysDAOImpl extends ObjDAOImpl<Pays> implements PaysDAO {

	public PaysDAOImpl(EntityManager connection) throws DAOException {
		super(connection, Pays.class);
	}

}
