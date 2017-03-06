package com.app.tennis.services.impl;

import com.app.tennis.dao.TournoiDAO;
import com.app.tennis.dao.impl.TournoiDAOImpl;
import com.app.tennis.data.Tournoi;
import com.app.tennis.services.TournoiService;

public class TournoiServiceImpl extends ObjServiceImpl<Tournoi> implements TournoiService {

	@Override
	public TournoiDAO getDAO() {
		return new TournoiDAOImpl();
	}
}
