package com.app.tennis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Arbitre;
import com.app.tennis.repository.ArbitreRepository;
import com.app.tennis.services.ArbitreService;

public class ArbitreServiceImpl extends ObjServiceImpl<Arbitre> implements ArbitreService {

	@Autowired
	private ArbitreRepository objRepository;
	
	@Override
	public JpaRepository<Arbitre, Integer> getRepository() {
		return this.objRepository;
	}

	
}
