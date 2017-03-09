package com.app.tennis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Acces;
import com.app.tennis.repository.AccesRepository;
import com.app.tennis.services.AccesService;

public class AccesServiceImpl extends ObjServiceImpl<Acces> implements AccesService {

	@Autowired
	private AccesRepository objRepository;
	
	@Override
	public JpaRepository<Acces, Integer> getRepository() {
		return this.objRepository;
	}
	
	
	@Override
	public Acces findByLogin(String login) {

		return ((AccesRepository) getRepository()).findByLogin(login);
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

	

}
