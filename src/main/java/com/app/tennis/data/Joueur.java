package com.app.tennis.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="joueurs")
@NamedQuery(name = "Joueur.findAll", query = "select p from Joueur p")
public class Joueur implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name = "id_joueur")
	@GeneratedValue
	private int id;

	@NotNull
	@Size(max = 30)
	private String nom;
	
	@NotNull
	@Size(max = 30)
	private String prenom;
	
	@NotNull
	private char sexe;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pays")
	private Pays pays;

	@NotNull
	private int classement;

	@NotNull
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + classement;
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((qualification == null) ? 0 : qualification.hashCode());
		result = prime * result + sexe;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (classement != other.classement)
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (pays == null) {
			if (other.pays != null)
				return false;
		} else if (!pays.equals(other.pays))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		if (sexe != other.sexe)
			return false;
		return true;
	}

}
