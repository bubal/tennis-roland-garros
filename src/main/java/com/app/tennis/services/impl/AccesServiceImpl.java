package com.app.tennis.services.impl;

import com.app.tennis.dao.AccesDAO;
import com.app.tennis.dao.impl.AccesDAOImpl;
import com.app.tennis.data.Acces;
import com.app.tennis.exceptions.DAOException;
import com.app.tennis.services.AccesService;

public class AccesServiceImpl extends ObjServiceImpl<Acces> implements AccesService {

	@Override
	public Acces findByLogin(String login) throws DAOException {
		return getDAO().findByLogin(login);
	}

	@Override
	public AccesDAO getDAO() {
		return new AccesDAOImpl();
	}

}
