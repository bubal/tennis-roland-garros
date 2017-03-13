package com.app.tennis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.tennis.data.Joueur;
import com.app.tennis.repository.JoueurRepository;
import com.app.tennis.services.JoueurService;

@Service
public class JoueurServiceImpl extends ObjServiceImpl<Joueur> implements JoueurService {

	@Autowired
	private JoueurRepository objRepository;
	
	@Override
	public JpaRepository<Joueur, Integer> getRepository() {
		return this.objRepository;
	}

	

}
