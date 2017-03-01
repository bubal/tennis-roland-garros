package com.app.tennis.dao.impl;

import javax.persistence.EntityManager;

import com.app.tennis.dao.ArbitreDAO;
import com.app.tennis.data.Arbitre;
import com.app.tennis.exceptions.DAOException;

public class ArbitreDAOImpl extends ObjDAOImpl<Arbitre> implements ArbitreDAO {

	public ArbitreDAOImpl(EntityManager connection) throws DAOException {
		super(connection, Arbitre.class);
	}

}
