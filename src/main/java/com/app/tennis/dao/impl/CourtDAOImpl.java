package com.app.tennis.dao.impl;

import com.app.tennis.dao.CourtDAO;
import com.app.tennis.data.Court;

public class CourtDAOImpl extends ObjDAOImpl<Court> implements CourtDAO {

	@Override
	Class<Court> getTypeClass() {
		return Court.class;
	}
}
