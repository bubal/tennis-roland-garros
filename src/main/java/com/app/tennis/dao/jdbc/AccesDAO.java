package com.app.tennis.dao.jdbc;

import static com.app.tennis.dao.DAOUtilities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Acces;
import com.app.tennis.exceptions.DAOException;

public class AccesDAO implements DAO<Acces>{

	private DAOFactory  daoFactory;

	public AccesDAO() {
		super();
	}

	public AccesDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Acces> listAll() throws DAOException {

		List<Acces> listeAcces = new ArrayList<Acces>();
		
		Connection connection = null;
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			connection = daoFactory.getConnection();
			String reqSql = "SELECT * FROM acces"; 
			preparedSt = connection.prepareStatement(reqSql);  
			result = preparedSt.executeQuery(); 

			while (result.next()){
				Acces acces = new Acces();
				acces.setLogin(result.getString("login"));
				acces.setPassword(result.getString("password"));
				acces.setId(result.getInt("id_acces"));
				listeAcces.add(acces);
			}
		} catch ( SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return listeAcces;
	}

	@Override
	public Acces create(Acces obj) throws DAOException{

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "INSERT INTO acces(login,password) VALUES(?,?)";  
			preparedSt = connection.prepareStatement(reqSql , Statement.RETURN_GENERATED_KEYS); 
			preparedSt.setString(1, obj.getLogin());
			preparedSt.setString(2, obj.getPassword());
			preparedSt.executeUpdate();
			ResultSet rsKey = preparedSt.getGeneratedKeys();
			
			if (rsKey.next()) {
				obj.setId(rsKey.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un accès." );
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
	public void delete(int id) throws DAOException{

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try{
			String reqSql = "DELETE From acces WHERE id_acces=?";
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
	public Acces update(Acces obj) throws DAOException{

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "UPDATE acces SET login=?, password=? WHERE id_acces=?";  
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setString(1, obj.getLogin());
			preparedSt.setString(2, obj.getPassword());
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
	public Acces find(String champs,String param) throws DAOException{

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null; 
		Acces obj = new Acces();

		try {
			String reqSql = "SELECT * FROM acces WHERE "+champs+"=?";  
			preparedSt = connection.prepareStatement(reqSql);  
			preparedSt.setString(1,param);
			result = preparedSt.executeQuery(); 
			if (result.next()){
				obj.setPassword(result.getString("password"));
				obj.setExist(true);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return obj;

	}

	@Override
	public Acces find(int id) throws DAOException {
		throw new DAOException("Méthode non implémenté dans la DAO.");
		//return null;
	}

}
