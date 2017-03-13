package com.app.tennis.services;

import com.app.tennis.data.Acces;

public interface AccesService extends ObjService<Acces> {
	
	Acces grantedAcces(String login, String password);

	Acces findByLogin(String string);

}