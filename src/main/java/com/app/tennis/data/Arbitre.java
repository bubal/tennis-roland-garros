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
@Table(name="arbitres")
@NamedQuery(name = "Arbitre.findAll", query = "select p from Arbitre p")
public class Arbitre{

	@Id 
	@Column(name = "id_arbitre")
	@GeneratedValue
	private int id;

	private String nom;
	private String prenom;
	private char sexe;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pays")
	private Pays pays;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_niveau")
	private NiveauArbitre niveau;

	public Arbitre() {
		super();
	}

	public Arbitre(String nom, String prenom, String sexe, Pays pays, NiveauArbitre niveau) {
		this.id = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe.charAt(0);
		this.pays = pays;
		this.niveau = niveau;
	}

	public NiveauArbitre getNiveau() {
		return niveau;
	}

	public void setNiveau(NiveauArbitre niveau) {
		this.niveau = niveau;
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
		return "Arbitre [id=" + this.getId() + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom() + ", sexe=" + this.getSexe() + ", pays=" + this.getPays() +", niveau=" + this.getNiveau() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((niveau == null) ? 0 : niveau.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		Arbitre other = (Arbitre) obj;
		if (id != other.id)
			return false;
		if (niveau == null) {
			if (other.niveau != null)
				return false;
		} else if (!niveau.equals(other.niveau))
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
		if (sexe != other.sexe)
			return false;
		return true;
	}


}
