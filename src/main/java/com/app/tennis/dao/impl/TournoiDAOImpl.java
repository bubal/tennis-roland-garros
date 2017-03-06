package com.app.tennis.dao.impl;

import com.app.tennis.dao.TournoiDAO;
import com.app.tennis.data.Tournoi;

public class TournoiDAOImpl extends ObjDAOImpl<Tournoi> implements TournoiDAO {

	@Override
	Class<Tournoi> getTypeClass() {
		return Tournoi.class;
	}
}
