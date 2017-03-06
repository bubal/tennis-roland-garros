package com.app.tennis.services.impl;

import com.app.tennis.dao.NiveauArbitreDAO;
import com.app.tennis.dao.impl.NiveauArbitreDAOImpl;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.services.NiveauArbitreService;

public class NiveauArbitreServiceImpl extends ObjServiceImpl<NiveauArbitre> implements NiveauArbitreService {

	@Override
	public NiveauArbitreDAO getDAO() {
		return new NiveauArbitreDAOImpl();
	}
}
