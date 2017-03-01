package com.app.tennis.dao.impl;

import javax.persistence.EntityManager;

import com.app.tennis.dao.JoueurDAO;
import com.app.tennis.data.Joueur;
import com.app.tennis.exceptions.DAOException;

public class JoueurDAOImpl extends ObjDAOImpl<Joueur> implements JoueurDAO {

	public JoueurDAOImpl(EntityManager connection) throws DAOException {
		super(connection, Joueur.class);
	}

}
