package com.app.tennis.dao.impl;

import javax.persistence.EntityManager;

import com.app.tennis.dao.CourtDAO;
import com.app.tennis.data.Court;
import com.app.tennis.exceptions.DAOException;

public class CourtDAOImpl extends ObjDAOImpl<Court> implements CourtDAO {

	public CourtDAOImpl(EntityManager connection) throws DAOException {
		super(connection, Court.class);
	}

	

}
