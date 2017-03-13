package com.app.tennis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.repository.NiveauArbitreRepository;
import com.app.tennis.services.NiveauArbitreService;

@Service
public class NiveauArbitreServiceImpl extends ObjServiceImpl<NiveauArbitre> implements NiveauArbitreService {

	@Autowired
	private NiveauArbitreRepository objRepository;
	
	@Override
	public JpaRepository<NiveauArbitre, Integer> getRepository() {
		return this.objRepository;
	}

}
