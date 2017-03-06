package com.app.tennis.services;

import com.app.tennis.data.Acces;
import com.app.tennis.exceptions.DAOException;

public interface AccesService extends ObjService<Acces> {
	
	Acces findByLogin(String login) throws DAOException;

}