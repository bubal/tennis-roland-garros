package com.app.tennis.dao.impl;

import com.app.tennis.dao.PaysDAO;
import com.app.tennis.data.Pays;

public class PaysDAOImpl extends ObjDAOImpl<Pays> implements PaysDAO {

	@Override
	Class<Pays> getTypeClass() {
		return Pays.class;
	}
}
