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

		
}
