package com.app.tennis.dao.impl;

import javax.persistence.EntityManager;

import com.app.tennis.dao.MatchDAO;
import com.app.tennis.data.Match;
import com.app.tennis.exceptions.DAOException;

public class MatchDAOImpl extends ObjDAOImpl<Match> implements MatchDAO {

	public MatchDAOImpl(EntityManager connection) throws DAOException {
		super(connection, Match.class);
	}

}
