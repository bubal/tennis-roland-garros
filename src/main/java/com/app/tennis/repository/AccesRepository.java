package com.app.tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Acces;

public interface AccesRepository extends JpaRepository<Acces, Integer> {

	Acces findByLogin(String login);
	
}
