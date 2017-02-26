package com.app.tennis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="joueurs")
@NamedQuery(name = "Joueur.findAll", query = "select p from Joueur p")
public class Joueur{
	
	@Id 
	@Column(name = "id_joueur")
	@GeneratedValue
	private int id;
	private String nom;
	private String prenom;
	private char sexe;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pays")
	private Pays pays;
	
	private int classement;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_qualification")
	private TypeQualification qualification;


	public Joueur() {
		super();
	}

	public Joueur(String nom, String prenom, String sexe, Pays pays, int classement, TypeQualification qualification) {
		this.id = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe.charAt(0);
		this.pays = pays;
		this.classement = classement;
		this.qualification = qualification;
	}


	public int getClassement() {
		return classement;
	}
	
	public void setClassement(int classement) {
		this.classement = classement;
	}

	public TypeQualification getQualification() {
		return qualification;
	}

	public void setQualification(TypeQualification qualification) {
		this.qualification = qualification;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return Character.toString(sexe);
	}

	public void setSexe(String sexe) {
		if (sexe.charAt(0)== 'H' || sexe.charAt(0)== 'F' )
			this.sexe = sexe.charAt(0);
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	@Override
	public String toString() {
		return "Joueur [id=" + this.getId() + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom() + ", sexe=" + this.getSexe() + ", pays=" + this.getPays()
				+ ", classement=" + this.getClassement() + ", qualification=" + this.getQualification() + "]";
	}


}
