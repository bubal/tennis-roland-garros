package com.app.tennis.dao.jdbc;

import static com.app.tennis.dao.DAOUtilities.closeSecureAll;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Arbitre;
import com.app.tennis.data.Court;
import com.app.tennis.data.Joueur;
import com.app.tennis.data.Match;
import com.app.tennis.data.Tournoi;
import com.app.tennis.exceptions.DAOException;

public class MatchDAO implements DAO<Match> {
	
	private DAOFactory  daoFactory;

	public MatchDAO() {
		super();
	}

	public MatchDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public Match create(Match obj) throws DAOException {

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "INSERT INTO matchs(id_tournoi,id_court,id_arbitre,id_joueur1,id_joueur2,date,heure_debut,heure_fin,sets_joueur1,sets_joueur2) ";
			reqSql += "VALUES(?,?,?,?,?,?,?,?,?,?)";  
			preparedSt = connection.prepareStatement(reqSql , Statement.RETURN_GENERATED_KEYS); 
			preparedSt.setLong(1, obj.getTournoi().getId());
			preparedSt.setLong(2, obj.getCourt().getId());
			preparedSt.setLong(3, obj.getArbitre().getId());
			preparedSt.setLong(4, obj.getJoueur1().getId());
			preparedSt.setLong(5, obj.getJoueur2().getId());
			preparedSt.setString(6, obj.getDate());
			preparedSt.setString(7, obj.getHeure_debut());
			preparedSt.setString(8, obj.getHeure_fin());
			preparedSt.setLong(9, obj.getSets_joueur1());
			preparedSt.setLong(10, obj.getSets_joueur2());

			preparedSt.executeUpdate();

			ResultSet rsKey = preparedSt.getGeneratedKeys();
			if (rsKey.next()) {
				obj.setId(rsKey.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un match." );
			}
			rsKey.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return obj;
	}

	@Override
	public List<Match> listAll() throws DAOException {

		List<Match> listeMatchs = new ArrayList<Match>();
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "SELECT * From matchs";
			preparedSt = connection.prepareStatement(reqSql);
			result = preparedSt.executeQuery(); 
			while (result.next()){
				Match match = new Match();
				DAO<Joueur> joueurDao = daoFactory.getObjJDBC(Joueur.class);
				DAO<Arbitre> arbitreDao = daoFactory.getObjJDBC(Arbitre.class);
				DAO<Court> courtDao = daoFactory.getObjJDBC(Court.class);
				DAO<Tournoi> tournoiDao = daoFactory.getObjJDBC(Tournoi.class);
				
				match.setId(result.getInt("id_match"));
				match.setTournoi(tournoiDao.find(result.getInt("id_tournoi")));
				match.setCourt(courtDao.find(result.getInt("id_court")));
				match.setArbitre(arbitreDao.find(result.getInt("id_arbitre")));
				match.setJoueur1(joueurDao.find(result.getInt("id_joueur1")));
				match.setJoueur2(joueurDao.find(result.getInt("id_joueur2")));
				match.setDate(result.getString("date"));
				match.setHeure_debut(result.getString("heure_debut"));
				match.setHeure_fin(result.getString("heure_fin"));
				match.setSets_joueur1(result.getLong("sets_joueur1"));
				match.setSets_joueur2(result.getLong("sets_joueur2"));
				listeMatchs.add(match);
			}
		
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return listeMatchs;
	}

	@Override
	public void delete(int id) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try{
			String reqSql = "DELETE From matchs WHERE id_match=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1,id);
			preparedSt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}

	}

	@Override
	public Match update(Match obj) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "UPDATE matchs SET id_tournoi=?, id_court=?, id_arbitre=?, id_joueur1=?, id_joueur2=?, date=?, heure_debut=?, heure_fin=?, sets_joueur1=?, sets_joueur2=? WHERE id_match=?";  
			preparedSt = connection.prepareStatement(reqSql); 
			preparedSt.setInt(1, obj.getTournoi().getId());
			preparedSt.setInt(2, obj.getCourt().getId());
			preparedSt.setInt(3, obj.getArbitre().getId());
			preparedSt.setInt(4, obj.getJoueur1().getId());
			preparedSt.setInt(5, obj.getJoueur2().getId());
			preparedSt.setString(6, obj.getDate());
			preparedSt.setString(7, obj.getHeure_debut());
			preparedSt.setString(8, obj.getHeure_fin());
			preparedSt.setLong(9, obj.getSets_joueur1());
			preparedSt.setLong(10, obj.getSets_joueur2());
			preparedSt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return obj;
	}

	@Override
	public Match find(int id) throws DAOException {
		
		Match match = null;
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "SELECT * From matchs WHERE id_match=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1,id);
			result = preparedSt.executeQuery(); 
			while (result.next()){
				match = new Match();
				DAO<Joueur> joueurDao = daoFactory.getObjJDBC(Joueur.class);
				DAO<Arbitre> arbitreDao = daoFactory.getObjJDBC(Arbitre.class);
				DAO<Court> courtDao = daoFactory.getObjJDBC(Court.class);
				DAO<Tournoi> tournoiDao = daoFactory.getObjJDBC(Tournoi.class);
				
				match.setId(result.getInt("id_match"));
				match.setTournoi(tournoiDao.find(result.getInt("id_tournoi")));
				match.setCourt(courtDao.find(result.getInt("id_court")));
				match.setArbitre(arbitreDao.find(result.getInt("id_arbitre")));
				match.setJoueur1(joueurDao.find(result.getInt("id_joueur1")));
				match.setJoueur2(joueurDao.find(result.getInt("id_joueur2")));
				match.setDate(result.getString("date"));
				match.setHeure_debut(result.getString("heure_debut"));
				match.setHeure_fin(result.getString("heure_fin"));
				match.setSets_joueur1(result.getLong("sets_joueur1"));
				match.setSets_joueur2(result.getLong("sets_joueur2"));
			}
		
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return match;
	}

	@Override
	public Match find(String champs,String param) throws DAOException {
		throw new DAOException("Méthode non implémenté dans la DAO.");
		//return null;
	}

}
