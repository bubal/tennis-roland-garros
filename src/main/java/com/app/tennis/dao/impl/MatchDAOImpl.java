package com.app.tennis.dao.impl;

import com.app.tennis.dao.MatchDAO;
import com.app.tennis.data.Match;

public class MatchDAOImpl extends ObjDAOImpl<Match> implements MatchDAO {

	@Override
	Class<Match> getTypeClass() {
		return Match.class;
	}
}
