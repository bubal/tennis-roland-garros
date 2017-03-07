package com.app.tennis.services.impl;

import com.app.tennis.dao.AccesDAO;
import com.app.tennis.dao.impl.AccesDAOImpl;
import com.app.tennis.data.Acces;
import com.app.tennis.services.AccesService;

public class AccesServiceImpl extends ObjServiceImpl<Acces> implements AccesService {

	@Override
	public Acces findByLogin(String login) {

		return getDAO().findByLogin(login);
	}

	@Override
	public Acces grantedAcces(String login, String password) {
		
		Acces user = findByLogin(login);
		
		if (!user.isExist()){
			user.setError("Le login "+ user.getLogin() + " n'existe pas!");
		}
		
		if (user.isExist() && !user.isAcces(password)){
				user.setError("Mot de passe érroné !");
		}

		return user;
	}
	
	@Override
	public AccesDAO getDAO() {
		return new AccesDAOImpl();
	}

}
