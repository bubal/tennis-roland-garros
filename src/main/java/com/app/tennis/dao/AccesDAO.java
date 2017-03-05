package com.app.tennis.dao;

import com.app.tennis.data.Acces;
import com.app.tennis.exceptions.DAOException;

public interface AccesDAO extends DAO<Acces> {

	Acces findByLogin(String login) throws DAOException;
	
}
