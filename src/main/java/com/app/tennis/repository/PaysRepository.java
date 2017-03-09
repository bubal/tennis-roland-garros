package com.app.tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Pays;

public interface PaysRepository extends JpaRepository<Pays, Integer> {

}
