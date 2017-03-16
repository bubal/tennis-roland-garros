package com.app.tennis.services;

import java.util.List;

import com.app.tennis.data.Joueur;

public interface JoueurService extends ObjService<Joueur> {
	
	Joueur findByIdFetchForRest(int id);
	List<Joueur> listAllFetchForRest();
}
