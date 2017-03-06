package com.app.tennis.services.impl;

import com.app.tennis.dao.PaysDAO;
import com.app.tennis.dao.impl.PaysDAOImpl;
import com.app.tennis.data.Pays;
import com.app.tennis.services.PaysService;

public class PaysServiceImpl extends ObjServiceImpl<Pays> implements PaysService {

	@Override
	public PaysDAO getDAO() {
		return new PaysDAOImpl();
	}
}
