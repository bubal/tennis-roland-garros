package com.app.tennis.dao.impl;

import com.app.tennis.dao.ArbitreDAO;
import com.app.tennis.data.Arbitre;

public class ArbitreDAOImpl extends ObjDAOImpl<Arbitre> implements ArbitreDAO {

	@Override
	Class<Arbitre> getTypeClass() {
		return Arbitre.class;
	}
}
