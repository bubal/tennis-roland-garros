package com.app.tennis.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.tennis.data.Match;
import com.app.tennis.repository.MatchRepository;
import com.app.tennis.services.MatchService;

@Service
public class MatchServiceImpl extends ObjServiceImpl<Match> implements MatchService {

	@Autowired
	private MatchRepository objRepository;
	
	@Override
	public JpaRepository<Match, Integer> getRepository() {
		return this.objRepository;
	}

	@Override
	public Match findByIdFetchForRest(int id) {
		return objRepository.findByIdFetchForRest(id);
	}

	@Override
	public List<Match> listAllFetchForRest() {
		return objRepository.listAllFetchForRest();
	}

	
}
