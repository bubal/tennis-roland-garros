package com.app.tennis.dao.impl;

import com.app.tennis.dao.JoueurDAO;
import com.app.tennis.data.Joueur;

public class JoueurDAOImpl extends ObjDAOImpl<Joueur> implements JoueurDAO {

	@Override
	Class<Joueur> getTypeClass() {
		return Joueur.class;
	}
}
