package com.app.tennis.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.tennis.data.Arbitre;
import com.app.tennis.repository.ArbitreRepository;
import com.app.tennis.services.ArbitreService;

@Service
public class ArbitreServiceImpl extends ObjServiceImpl<Arbitre> implements ArbitreService {

	@Autowired
	private ArbitreRepository objRepository;
	
	@Override
	public JpaRepository<Arbitre, Integer> getRepository() {
		return this.objRepository;
	}

	@Override
	public Arbitre findByIdFetchAll(int id) {
		return objRepository.findByIdFetchAll(id);
	}

	@Override
	public List<Arbitre> listAllFetchAll() {
		return objRepository.listAllFetchAll();
	}

	
}
