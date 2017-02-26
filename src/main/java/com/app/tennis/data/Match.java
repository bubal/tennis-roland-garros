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
@Table(name="matchs")
@NamedQuery(name = "Match.findAll", query = "select p from Match p")
public class Match {
	
	@Id 
	@Column(name = "id_match")
	@GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tournoi")
	private Tournoi tournoi;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_court")
	private Court court;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_arbitre")
	private Arbitre arbitre;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_joueur1")
	private Joueur joueur1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_joueur2")
	private Joueur joueur2;
	
	private String date;
	private String heure_debut;
	private String heure_fin;
	private Long sets_joueur1;
	private Long sets_joueur2;
	
	public Match() {
		super();
	}

	public Match(Tournoi tournoi, Court court, Arbitre arbitre, Joueur joueur1, Joueur joueur2, String date,
			String heure_debut, String heure_fin, Long sets_joueur1, Long sets_joueur2) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(String heure_debut) {
		this.heure_debut = heure_debut;
	}

	public String getHeure_fin() {
		return heure_fin;
	}

	public void setHeure_fin(String heure_fin) {
		this.heure_fin = heure_fin;
	}

	public Long getSets_joueur1() {
		return sets_joueur1;
	}

	public void setSets_joueur1(Long sets_joueur1) {
		this.sets_joueur1 = sets_joueur1;
	}

	public Long getSets_joueur2() {
		return sets_joueur2;
	}

	public void setSets_joueur2(Long sets_joueur2) {
		this.sets_joueur2 = sets_joueur2;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", tournoi=" + tournoi + ", court=" + court + ", arbitre=" + arbitre + ", joueur1="
				+ joueur1 + ", joueur2=" + joueur2 + ", date=" + date + ", heure_debut=" + heure_debut + ", heure_fin="
				+ heure_fin + ", sets_joueur1=" + sets_joueur1 + ", sets_joueur2=" + sets_joueur2
				+ "]";
	}
	
	
	
}
