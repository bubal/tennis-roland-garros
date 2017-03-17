package com.app.tennis.services;

import java.util.List;

import com.app.tennis.data.Arbitre;

public interface ArbitreService extends ObjService<Arbitre> {

	Arbitre findByIdFetchForRest(int id);
	List<Arbitre> listAllFetchForRest();
}
