package com.app.tennis.dao.jdbc;

import static com.app.tennis.dao.DAOUtilities.closeSecureAll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Tournoi;
import com.app.tennis.exceptions.DAOException;

public class TournoiDAO implements DAO<Tournoi> {

	private DAOFactory  daoFactory;
	
	public TournoiDAO() {
		super();
	}

	public TournoiDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public Tournoi create(Tournoi obj) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "INSERT INTO tournois(nom,nbr_sets) VALUES(?,?)";  
			preparedSt = connection.prepareStatement(reqSql , Statement.RETURN_GENERATED_KEYS); 
			preparedSt.setString(1,obj.getNom());
			preparedSt.setLong(2,obj.getNbr_sets());
			preparedSt.executeUpdate();
			ResultSet rsKey = preparedSt.getGeneratedKeys();
			
			if (rsKey.next()) {
				obj.setId(rsKey.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un court." );
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
	public List<Tournoi> listAll() throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		List<Tournoi> listeTournois = new ArrayList<Tournoi>();
		
		try {
			String reqSql = "SELECT * From tournois";
			preparedSt = connection.prepareStatement(reqSql);
			result = preparedSt.executeQuery(); 
			while (result.next()){
				Tournoi tournoi = new Tournoi();
				tournoi.setNom(result.getString("nom"));
				tournoi.setNbr_sets(result.getLong("nbr_sets"));
				tournoi.setId(result.getInt("id_tournoi"));
				listeTournois.add(tournoi);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return listeTournois;
	}

	@Override
	public void delete(int id) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try{
			String reqSql = "DELETE From tournois WHERE id_tournoi=?";
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
	public Tournoi update(Tournoi obj) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "UPDATE tournois SET nom=?, nbr_sets=? WHERE id_tournoi=?";  
			preparedSt = connection.prepareStatement(reqSql); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.setLong(2, obj.getNbr_sets());
			preparedSt.setInt(3, obj.getId());
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
	public Tournoi find(int id) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		Tournoi tournoi = null;
		
		try {
			String reqSql = "SELECT * From tournois WHERE id_tournoi=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1,id);
			result = preparedSt.executeQuery(); 
			while (result.next()){
				tournoi = new Tournoi();
				tournoi.setNom(result.getString("nom"));
				tournoi.setNbr_sets(result.getLong("nbr_sets"));
				tournoi.setId(result.getInt("id_tournoi"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return tournoi;
	}
	
	@Override
	public Tournoi find(String champs,String param) throws DAOException {
		throw new DAOException("Méthode non implémenté dans la DAO.");
		//return null;
	}

}
