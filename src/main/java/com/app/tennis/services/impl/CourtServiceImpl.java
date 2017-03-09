package com.app.tennis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Court;
import com.app.tennis.repository.CourtRepository;
import com.app.tennis.services.CourtService;

public class CourtServiceImpl extends ObjServiceImpl<Court> implements CourtService {

	@Autowired
	private CourtRepository objRepository;
	
	@Override
	public JpaRepository<Court, Integer> getRepository() {
		return this.objRepository;
	}

	
}
