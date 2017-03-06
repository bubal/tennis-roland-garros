package com.app.tennis.services.impl;

import com.app.tennis.dao.MatchDAO;
import com.app.tennis.dao.impl.MatchDAOImpl;
import com.app.tennis.data.Match;
import com.app.tennis.services.MatchService;

public class MatchServiceImpl extends ObjServiceImpl<Match> implements MatchService {

	@Override
	public MatchDAO getDAO() {
		return new MatchDAOImpl();
	}
}
