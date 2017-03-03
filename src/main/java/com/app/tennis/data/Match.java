package com.app.tennis.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="matchs")
@NamedQuery(name = "Match.findAll", query = "select p from Match p")
public class Match implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name = "id_match")
	@GeneratedValue
	private int id;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tournoi")
	private Tournoi tournoi;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_court")
	private Court court;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_arbitre")
	private Arbitre arbitre;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_joueur1")
	private Joueur joueur1;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_joueur2")
	private Joueur joueur2;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private Timestamp heure_debut;
	
	private Timestamp heure_fin;
	
	@NotNull
	private int sets_joueur1;
	
	@NotNull
	private int sets_joueur2;

	
	public Match() {
		super();
	}

	public Match(Tournoi tournoi, Court court, Arbitre arbitre, Joueur joueur1, Joueur joueur2, Date date,
			Timestamp heure_debut, Timestamp heure_fin, int sets_joueur1, int sets_joueur2) {
		super();
		this.id = 0;
		this.tournoi = tournoi;
		this.court = court;
		this.arbitre = arbitre;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.date = date;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
		this.sets_joueur1 = sets_joueur1;
		this.sets_joueur2 = sets_joueur2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Arbitre getArbitre() {
		return arbitre;
	}

	public void setArbitre(Arbitre arbitre) {
		this.arbitre = arbitre;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(Timestamp heure_debut) {
		this.heure_debut = heure_debut;
	}

	public Timestamp getHeure_fin() {
		return heure_fin;
	}

	public void setHeure_fin(Timestamp heure_fin) {
		this.heure_fin = heure_fin;
	}

	public int getSets_joueur1() {
		return sets_joueur1;
	}

	public void setSets_joueur1(int sets_joueur1) {
		this.sets_joueur1 = sets_joueur1;
	}

	public int getSets_joueur2() {
		return sets_joueur2;
	}

	public void setSets_joueur2(int sets_joueur2) {
		this.sets_joueur2 = sets_joueur2;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", tournoi=" + tournoi + ", court=" + court + ", arbitre=" + arbitre + ", joueur1="
				+ joueur1 + ", joueur2=" + joueur2 + ", date=" + date + ", heure_debut=" + heure_debut + ", heure_fin="
				+ heure_fin + ", sets_joueur1=" + sets_joueur1 + ", sets_joueur2=" + sets_joueur2
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arbitre == null) ? 0 : arbitre.hashCode());
		result = prime * result + ((court == null) ? 0 : court.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((heure_debut == null) ? 0 : heure_debut.hashCode());
		result = prime * result + ((heure_fin == null) ? 0 : heure_fin.hashCode());
		result = prime * result + id;
		result = prime * result + ((joueur1 == null) ? 0 : joueur1.hashCode());
		result = prime * result + ((joueur2 == null) ? 0 : joueur2.hashCode());
		result = prime * result + sets_joueur1;
		result = prime * result + sets_joueur2;
		result = prime * result + ((tournoi == null) ? 0 : tournoi.hashCode());
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
		Match other = (Match) obj;
		if (arbitre == null) {
			if (other.arbitre != null)
				return false;
		} else if (!arbitre.equals(other.arbitre))
			return false;
		if (court == null) {
			if (other.court != null)
				return false;
		} else if (!court.equals(other.court))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (heure_debut == null) {
			if (other.heure_debut != null)
				return false;
		} else if (!heure_debut.equals(other.heure_debut))
			return false;
		if (heure_fin == null) {
			if (other.heure_fin != null)
				return false;
		} else if (!heure_fin.equals(other.heure_fin))
			return false;
		if (id != other.id)
			return false;
		if (joueur1 == null) {
			if (other.joueur1 != null)
				return false;
		} else if (!joueur1.equals(other.joueur1))
			return false;
		if (joueur2 == null) {
			if (other.joueur2 != null)
				return false;
		} else if (!joueur2.equals(other.joueur2))
			return false;
		if (sets_joueur1 != other.sets_joueur1)
			return false;
		if (sets_joueur2 != other.sets_joueur2)
			return false;
		if (tournoi == null) {
			if (other.tournoi != null)
				return false;
		} else if (!tournoi.equals(other.tournoi))
			return false;
		return true;
	}

	

}
