package com.app.tennis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="courts")
@NamedQuery(name = "Court.findAll", query = "select p from Court p")
public class Court {
	
	@Id 
	@Column(name = "id_court")
	@GeneratedValue
	private int id;
	private String nom;

	
	public Court() {
		super();
		
	}
	
	public Court(String nom) {
		super();
		this.id = 0;
		this.nom = nom;
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

	@Override
	public String toString() {
		return "Court [id=" + id + ", nom=" + nom + "]";
	}
	
	
	
}
