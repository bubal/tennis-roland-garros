package com.app.tennis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="types_qualifications")
@NamedQuery(name = "TypeQualification.findAll", query = "select p from TypeQualification p")
public class TypeQualification {
	
	@Id 
	@Column(name = "id_qualification")
	@GeneratedValue
	private int id;
	private String nom;
	
	public TypeQualification() {
		super();
	}
	
	public TypeQualification(String nom) {
		super();
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
		return "TypeQualification [nom=" + nom + "]";
	}
	
}
