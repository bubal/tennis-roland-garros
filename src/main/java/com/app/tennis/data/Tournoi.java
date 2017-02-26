package com.app.tennis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="tournois")
@NamedQuery(name = "Tournoi.findAll", query = "select p from Tournoi p")
public class Tournoi {
	
	@Id 
	@Column(name = "id_tournoi")
	@GeneratedValue
	private int id;
	private String nom;
	private Long nbr_sets;
	
	public Tournoi(){
		super();
	}
	
	public Tournoi(String nom, Long nbr_sets) {
		super();
		this.id = 0;
		this.nom = nom;
		this.nbr_sets = nbr_sets;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Long getNbr_sets() {
		return nbr_sets;
	}
	public void setNbr_sets(Long nbr_sets) {
		this.nbr_sets = nbr_sets;
	}

	@Override
	public String toString() {
		return "Tournoi [id=" + id + ", nom=" + nom + ", nbr_sets=" + nbr_sets + "]";
	}
	
	
	
}
