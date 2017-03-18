package com.app.tennis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.tennis.data.Arbitre;

public interface ArbitreRepository extends JpaRepository<Arbitre, Integer> {
	
	@Query(value = "SELECT a FROM Arbitre a "
			+ "INNER JOIN FETCH a.pays "
			+ "INNER JOIN FETCH a.niveau "
			+ "WHERE a.id = (:id)")
	public Arbitre findByIdFetchForRest(@Param("id") int id);
	
	@Query(value = "SELECT a FROM Arbitre a "
			+ "INNER JOIN FETCH a.pays "
			+ "INNER JOIN FETCH a.niveau")
	public List<Arbitre> listAllFetchForRest();

}
