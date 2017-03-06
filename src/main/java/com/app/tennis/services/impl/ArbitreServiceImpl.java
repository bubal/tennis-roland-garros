package com.app.tennis.services.impl;

import com.app.tennis.dao.ArbitreDAO;
import com.app.tennis.dao.impl.ArbitreDAOImpl;
import com.app.tennis.data.Arbitre;
import com.app.tennis.services.ArbitreService;

public class ArbitreServiceImpl extends ObjServiceImpl<Arbitre> implements ArbitreService {

	@Override
	public ArbitreDAO getDAO() {
		return new ArbitreDAOImpl();
	}
}
