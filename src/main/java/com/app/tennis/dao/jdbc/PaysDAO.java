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
import com.app.tennis.data.Pays;
import com.app.tennis.exceptions.DAOException;

public class PaysDAO implements DAO<Pays> {
	
	private DAOFactory  daoFactory;

	public PaysDAO(){
		super();
	}
	public PaysDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public Pays create(Pays obj) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "INSERT INTO pays(nom) VALUES(?)";  
			preparedSt = connection.prepareStatement(reqSql , Statement.RETURN_GENERATED_KEYS); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.executeUpdate();

			ResultSet rsKey = preparedSt.getGeneratedKeys();
			if (rsKey.next()) {
				obj.setId(rsKey.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un pays." );
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
			String reqSql = "DELETE From pays WHERE id_pays=?";
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
	public Pays find(int id) throws DAOException {
		
		Pays pays = null;
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "SELECT * From pays WHERE id_pays=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1,id);
			result = preparedSt.executeQuery(); 
			
			while (result.next()){
				pays = new Pays();
				pays.setNom(result.getString("nom"));
				pays.setId(result.getInt("id_pays"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return pays;
	}

	@Override
	public Pays find(String champs,String param) throws DAOException {
		throw new DAOException("Méthode non implémenté dans la DAO.");
		//return null;
		
	}

	@Override
	public Pays update(Pays obj) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "UPDATE pays SET nom=? WHERE id_pays=?";  
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
	public List<Pays> listAll() throws DAOException {
		
		List<Pays> listePays = new ArrayList<Pays>();
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "SELECT * From pays";
			preparedSt = connection.prepareStatement(reqSql);
			result = preparedSt.executeQuery(); 
			
			while (result.next()){
				Pays pays = new Pays();
				pays.setNom(result.getString("nom"));
				pays.setId(result.getInt("id_pays"));
				listePays.add(pays);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return listePays;
	}

}
