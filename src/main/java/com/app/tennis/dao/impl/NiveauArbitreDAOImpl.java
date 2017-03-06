package com.app.tennis.dao.impl;

import com.app.tennis.dao.NiveauArbitreDAO;
import com.app.tennis.data.NiveauArbitre;

public class NiveauArbitreDAOImpl extends ObjDAOImpl<NiveauArbitre> implements NiveauArbitreDAO {

	@Override
	Class<NiveauArbitre> getTypeClass() {
		return NiveauArbitre.class;
	}
}
