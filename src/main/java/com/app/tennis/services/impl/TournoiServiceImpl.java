package com.app.tennis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.tennis.data.Tournoi;
import com.app.tennis.repository.TournoiRepository;
import com.app.tennis.services.TournoiService;

@Service
public class TournoiServiceImpl extends ObjServiceImpl<Tournoi> implements TournoiService {

	@Autowired
	private TournoiRepository objRepository;
	
	@Override
	public JpaRepository<Tournoi, Integer> getRepository() {
		return this.objRepository;
	}

}
