package com.app.tennis.dao.impl;

import javax.persistence.EntityManager;

import com.app.tennis.dao.TournoiDAO;
import com.app.tennis.data.Tournoi;
import com.app.tennis.exceptions.DAOException;

public class TournoiDAOImpl extends ObjDAOImpl<Tournoi> implements TournoiDAO {

	public TournoiDAOImpl(EntityManager connection) throws DAOException {
		super(connection, Tournoi.class);
	}

}
