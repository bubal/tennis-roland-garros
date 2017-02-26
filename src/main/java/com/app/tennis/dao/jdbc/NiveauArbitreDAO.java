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
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.exceptions.DAOException;

public class NiveauArbitreDAO implements DAO<NiveauArbitre> {
	
	private DAOFactory  daoFactory;

	public NiveauArbitreDAO() {
		super();
	}

	public NiveauArbitreDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public NiveauArbitre create(NiveauArbitre obj) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "INSERT INTO niveau_arbitre(nom,description) VALUES(?,?)";  
			preparedSt = connection.prepareStatement(reqSql , Statement.RETURN_GENERATED_KEYS); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.setString(2, obj.getDescription());
			preparedSt.executeUpdate();

			ResultSet rsKey = preparedSt.getGeneratedKeys();
			if (rsKey.next()) {
				obj.setId(rsKey.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un niveau arbitre." );
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
			String reqSql = "DELETE From niveau_arbitre WHERE id_niveau=?";
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
	public NiveauArbitre find(int id) throws DAOException {
		
		NiveauArbitre niveau = null;
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "SELECT * From niveau_arbitre WHERE id_niveau=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1,id);
			result = preparedSt.executeQuery(); 
			
			while (result.next()){
				niveau = new NiveauArbitre();
				niveau.setNom(result.getString("nom"));
				niveau.setDescription(result.getString("description"));
				niveau.setId(result.getInt("id_niveau"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return niveau;
	}

	@Override
	public NiveauArbitre find(String champs,String param) throws DAOException {
		throw new DAOException("Méthode non implémenté dans la DAO.");
		//return null;
	}

	@Override
	public NiveauArbitre update(NiveauArbitre obj) throws DAOException {
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "UPDATE niveau_arbitre SET nom=?, description=? WHERE id_niveau=?";  
			preparedSt = connection.prepareStatement(reqSql); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.setString(2, obj.getDescription());
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
	public List<NiveauArbitre> listAll() throws DAOException {
		
		List<NiveauArbitre> listeNiveau = new ArrayList<NiveauArbitre>();
		
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;
		
		try {
			String reqSql = "SELECT * From niveau_arbitre";
			preparedSt = connection.prepareStatement(reqSql);
			result = preparedSt.executeQuery(); 
			
			while (result.next()){
				NiveauArbitre niveau = new NiveauArbitre();
				niveau.setNom(result.getString("nom"));
				niveau.setDescription(result.getString("description"));
				niveau.setId(result.getInt("id_niveau"));
				listeNiveau.add(niveau);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return listeNiveau;
	}

}
