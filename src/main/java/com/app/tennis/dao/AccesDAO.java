package com.app.tennis.dao;

import com.app.tennis.data.Acces;

public interface AccesDAO extends DAO<Acces> {

	Acces findByLogin(String login);
	
}
