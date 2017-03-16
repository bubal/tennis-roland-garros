package com.app.tennis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.tennis.data.Joueur;

public interface JoueurRepository extends JpaRepository<Joueur, Integer> {
	
	@Query(value = "SELECT j FROM Joueur j LEFT JOIN FETCH j.pays JOIN FETCH j.qualification WHERE j.id = (:id)")
	public Joueur findByIdFetchForRest(@Param("id") int id);
	
	@Query(value = "SELECT j FROM Joueur j LEFT JOIN FETCH j.pays JOIN FETCH j.qualification")
	public List<Joueur> listAllFetchForRest();

}
