package com.app.tennis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tennis.data.Acces;
import com.app.tennis.repository.AccesRepository;
import com.app.tennis.services.AccesService;

@Service
public class AccesServiceImpl extends ObjServiceImpl<Acces> implements AccesService {

	@Autowired
	private AccesRepository objRepository;
	
	@Override
	public AccesRepository getRepository() {
		return this.objRepository;
	}
	
	@Override
	public Acces findByLogin(String login) {
		Acces user = new Acces(login);
		Acces userFind = objRepository.findByLogin(login);
		if (userFind != null){
			user = userFind;
			user.setExist(true);
		}
		return user;
	}
	
	@Override
	public Acces grantedAcces(String login, String password) {
		
		Acces user = this.findByLogin(login);
		
		if (!user.isExist()){
			user.setError("Le login "+ user.getLogin() + " n'existe pas!");
		}
		
		if (user.isExist() && !user.isAcces(password)){
				user.setError("Mot de passe érroné !");
		}

		return user;
	}
	
}
