package com.app.tennis.services.impl;

import com.app.tennis.dao.CourtDAO;
import com.app.tennis.dao.impl.CourtDAOImpl;
import com.app.tennis.data.Court;
import com.app.tennis.services.CourtService;

public class CourtServiceImpl extends ObjServiceImpl<Court> implements CourtService {

	@Override
	public CourtDAO getDAO() {
		return new CourtDAOImpl();
	}
}
