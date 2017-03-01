package com.app.tennis.dao.impl;

import javax.persistence.EntityManager;

import com.app.tennis.dao.NiveauArbitreDAO;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.exceptions.DAOException;

public class NiveauArbitreDAOImpl extends ObjDAOImpl<NiveauArbitre> implements NiveauArbitreDAO {

	public NiveauArbitreDAOImpl(EntityManager connection) throws DAOException {
		super(connection, NiveauArbitre.class);
	}

}
