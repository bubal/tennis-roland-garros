package com.app.tennis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="niveau_arbitre")
@NamedQuery(name = "NiveauArbitre.findAll", query = "select p from NiveauArbitre p")
public class NiveauArbitre {

	@Id 
	@Column(name = "id_niveau")
	@GeneratedValue
	private int id;
	private String nom;
	private String description;
	
	
	public NiveauArbitre() {
		super();
	}

	public NiveauArbitre(String nom, String description) {
		super();
		this.id=0;
		this.nom = nom;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "NiveauArbitre [nom=" + nom + ", description=" + description + "]";
	}
	
}
