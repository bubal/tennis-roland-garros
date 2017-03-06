package com.app.tennis.services.impl;

import com.app.tennis.dao.JoueurDAO;
import com.app.tennis.dao.impl.JoueurDAOImpl;
import com.app.tennis.data.Joueur;
import com.app.tennis.services.JoueurService;

public class JoueurServiceImpl extends ObjServiceImpl<Joueur> implements JoueurService {

	@Override
	public JoueurDAO getDAO() {
		return new JoueurDAOImpl();
	}

}
