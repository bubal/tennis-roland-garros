package com.app.tennis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="pays")
@NamedQuery(name = "Pays.findAll", query = "select p from Pays p order by p.nom")
public class Pays {

	@Id 
	@Column(name = "id_pays")
	@GeneratedValue
	private int id;
	private String nom;
	
	public Pays(){
		super();
	}

	public Pays(String nom) {
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
		return "Pays [nom=" + nom + "]";
	}
	
	
	
	
}
