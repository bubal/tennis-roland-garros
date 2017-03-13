package com.app.tennis.services.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.tennis.data.Pays;
import com.app.tennis.repository.PaysRepository;
import com.app.tennis.services.PaysService;

@Service
public class PaysServiceImpl extends ObjServiceImpl<Pays> implements PaysService {

	private PaysRepository objRepository;
	
	@Override
	public JpaRepository<Pays, Integer> getRepository() {
		return this.objRepository;
	}

}
