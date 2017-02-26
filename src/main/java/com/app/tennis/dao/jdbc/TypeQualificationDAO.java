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
import com.app.tennis.data.TypeQualification;
import com.app.tennis.exceptions.DAOException;

public class TypeQualificationDAO implements DAO<TypeQualification> {

	private DAOFactory  daoFactory;

	public TypeQualificationDAO() {
		super();
	}

	public TypeQualificationDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public TypeQualification create(TypeQualification obj) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "INSERT INTO types_qualifications(nom) VALUES(?)";  
			preparedSt = connection.prepareStatement(reqSql , Statement.RETURN_GENERATED_KEYS); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.executeUpdate();

			ResultSet rsKey = preparedSt.getGeneratedKeys();
			if (rsKey.next()) {
				obj.setId(rsKey.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un type de qualification." );
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
			String reqSql = "DELETE From types_qualifications WHERE id_qualification=?";
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
	public TypeQualification find(int id) throws DAOException {
		
		TypeQualification qualification = null;
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "SELECT * From types_qualifications WHERE id_qualification=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1,id);
			result = preparedSt.executeQuery(); 
			
			while (result.next()){
				qualification = new TypeQualification();
				qualification.setNom(result.getString("nom"));
				qualification.setId(result.getInt("id_qualification"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return qualification;
	}

	@Override
	public TypeQualification find(String champs,String param) throws DAOException {
		throw new DAOException("Méthode non implémenté dans la DAO.");
		//return null;
	}

	@Override
	public TypeQualification update(TypeQualification obj) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "UPDATE types_qualifications SET nom=? WHERE id_qualification=?";  
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
	public List<TypeQualification> listAll() throws DAOException {
		
		List<TypeQualification> listeQualifications = new ArrayList<TypeQualification>();
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "SELECT * From types_qualifications";
			preparedSt = connection.prepareStatement(reqSql);
			result = preparedSt.executeQuery(); 
			
			while (result.next()){
				TypeQualification qualification = new TypeQualification();
				qualification.setNom(result.getString("nom"));
				qualification.setId(result.getInt("id_qualification"));
				listeQualifications.add(qualification);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return listeQualifications;
	}

}
