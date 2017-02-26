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
import com.app.tennis.data.Court;
import com.app.tennis.exceptions.DAOException;

public class CourtDAO implements DAO<Court> {

	private DAOFactory  daoFactory;
	
	public CourtDAO() {
		super();
	}

	public CourtDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public Court create(Court obj) throws DAOException {

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "INSERT INTO courts(nom) VALUES(?)";  
			preparedSt = connection.prepareStatement(reqSql , Statement.RETURN_GENERATED_KEYS); 
			preparedSt.setString(1, obj.getNom());
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
	public void delete(int id) throws DAOException {

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try{
			String reqSql = "DELETE From courts WHERE id_court=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1, id);
			preparedSt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}

	}

	@Override
	public Court update(Court obj) throws DAOException {

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "UPDATE courts SET nom=? WHERE id_court=?";  
			preparedSt = connection.prepareStatement(reqSql); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.setInt(2, obj.getId());
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
	public Court find(int id) throws DAOException {

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		Court court = null;
		
		try {
			String reqSql = "SELECT * From courts WHERE id_court=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1,id);
			result = preparedSt.executeQuery(); 
			while (result.next()){
				court = new Court();
				court.setNom(result.getString("nom"));
				court.setId(result.getInt("id_court"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return court;
	}

	@Override
	public List<Court> listAll() throws DAOException {

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		List<Court> listCourts = new ArrayList<Court>();

		try {
			String reqSql = "SELECT * FROM courts";  
			preparedSt = connection.prepareStatement(reqSql);  
			result = preparedSt.executeQuery(); 
			while (result.next()){
				Court court = new Court();
				court.setNom(result.getString("nom"));
				court.setId(result.getInt("id_court"));
				listCourts.add(court);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return listCourts;
	}

	@Override
	public Court find(String champs,String param) throws DAOException {
		throw new DAOException("Méthode non implémenté dans la DAO.");
		//return null;
	}

}
