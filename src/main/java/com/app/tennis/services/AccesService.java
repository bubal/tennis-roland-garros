package com.app.tennis.services;

import com.app.tennis.data.Acces;

public interface AccesService extends ObjService<Acces> {
	
	Acces findByLogin(String login);
	
	Acces grantedAcces(String login, String password);

}