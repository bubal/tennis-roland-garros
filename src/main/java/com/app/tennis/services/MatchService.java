package com.app.tennis.services;

import java.util.List;

import com.app.tennis.data.Match;

public interface MatchService extends ObjService<Match> {

	Match findByIdFetchForRest(int id);
	List<Match> listAllFetchForRest();
}
