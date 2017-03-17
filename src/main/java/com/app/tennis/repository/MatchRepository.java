package com.app.tennis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.tennis.data.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {
	
	@Query(value = "SELECT m FROM Match m LEFT JOIN FETCH m.tournoi JOIN FETCH m.court JOIN FETCH m.arbitre JOIN FETCH m.joueur1 JOIN FETCH m.joueur2 WHERE m.id = (:id)")
	public Match findByIdFetchForRest(@Param("id") int id);
	
	@Query(value = "SELECT m FROM Match m LEFT JOIN FETCH m.tournoi JOIN FETCH m.court JOIN FETCH m.arbitre JOIN FETCH m.joueur1 JOIN FETCH m.joueur2")
	public List<Match> listAllFetchForRest();

}
