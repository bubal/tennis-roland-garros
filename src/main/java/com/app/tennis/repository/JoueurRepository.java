package com.app.tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Joueur;

public interface JoueurRepository extends JpaRepository<Joueur, Integer> {

}
