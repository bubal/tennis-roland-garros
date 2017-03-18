package com.app.tennis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.tennis.data.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {
	
	@Query(value = "SELECT m FROM Match m "
			+ "INNER JOIN FETCH m.tournoi "
			+ "INNER JOIN FETCH m.court "
			+ "INNER JOIN FETCH m.arbitre a "
			+ "INNER JOIN FETCH a.pays "
			+ "INNER JOIN FETCH a.niveau "
			+ "INNER JOIN FETCH m.joueur1 j1 "
			+ "INNER JOIN FETCH j1.pays "
			+ "INNER JOIN FETCH j1.qualification "
			+ "INNER JOIN FETCH m.joueur2 j2 "
			+ "INNER JOIN FETCH j2.pays "
			+ "INNER JOIN FETCH j2.qualification "
			+ "WHERE m.id = (:id)")
	public Match findByIdFetchForRest(@Param("id") int id);
	
	@Query(value = "SELECT m FROM Match m "
			+ "INNER JOIN FETCH m.tournoi "
			+ "INNER JOIN FETCH m.court "
			+ "INNER JOIN FETCH m.arbitre a "
			+ "INNER JOIN FETCH a.pays "
			+ "INNER JOIN FETCH a.niveau "
			+ "INNER JOIN FETCH m.joueur1 j1 "
			+ "INNER JOIN FETCH j1.pays "
			+ "INNER JOIN FETCH j1.qualification "
			+ "INNER JOIN FETCH m.joueur2 j2 "
			+ "INNER JOIN FETCH j2.pays "
			+ "INNER JOIN FETCH j2.qualification ")
	public List<Match> listAllFetchForRest();

}
