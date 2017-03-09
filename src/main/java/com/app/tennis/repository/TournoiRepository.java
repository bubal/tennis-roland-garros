package com.app.tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.Tournoi;

public interface TournoiRepository extends JpaRepository<Tournoi, Integer> {

}
