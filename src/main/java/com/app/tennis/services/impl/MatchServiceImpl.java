package com.app.tennis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Match;
import com.app.tennis.repository.MatchRepository;
import com.app.tennis.services.MatchService;

public class MatchServiceImpl extends ObjServiceImpl<Match> implements MatchService {

	@Autowired
	private MatchRepository objRepository;
	
	@Override
	public JpaRepository<Match, Integer> getRepository() {
		return this.objRepository;
	}

	
}
