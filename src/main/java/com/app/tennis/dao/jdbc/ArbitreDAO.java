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
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.data.Pays;
import com.app.tennis.exceptions.DAOException;

public class ArbitreDAO implements DAO<Arbitre> {

	private DAOFactory  daoFactory;

	
	public ArbitreDAO() {
		super();
	}

	public ArbitreDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public Arbitre create(Arbitre obj) throws DAOException {

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "INSERT INTO arbitres(nom,prenom,sexe,id_pays,id_niveau) VALUES(?,?,?,?,?)";  
			preparedSt = connection.prepareStatement(reqSql , Statement.RETURN_GENERATED_KEYS); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.setString(2, obj.getPrenom());
			preparedSt.setString(3, obj.getSexe());
			preparedSt.setInt(4, obj.getPays().getId());
			preparedSt.setInt(5, obj.getNiveau().getId());
			preparedSt.executeUpdate();

			ResultSet rsKey = preparedSt.getGeneratedKeys();
			if (rsKey.next()) {
				obj.setId(rsKey.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un arbitre." );
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
	public List<Arbitre> listAll() throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		List<Arbitre> listeArbitres = new ArrayList<Arbitre>();

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;  

		try {
			String reqSql = "SELECT * FROM arbitres";  
			preparedSt = connection.prepareStatement(reqSql);  
			result = preparedSt.executeQuery(); 
			while (result.next()){
				Arbitre arbitre = new Arbitre();
				DAO<Pays> paysDao = daoFactory.getObjJDBC(Pays.class);
				DAO<NiveauArbitre> niveauDao = daoFactory.getObjJDBC(NiveauArbitre.class);
				arbitre.setNom(result.getString("nom"));
				arbitre.setPrenom(result.getString("prenom"));
				arbitre.setPays(paysDao.find(result.getInt("id_pays")));
				arbitre.setSexe(result.getString("sexe"));
				arbitre.setNiveau(niveauDao.find(result.getInt("id_niveau")));
				arbitre.setId(result.getInt("id_arbitre"));
				listeArbitres.add(arbitre);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return listeArbitres;
	}

	@Override
	public void delete(int id) throws DAOException {
		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try{
			String reqSql = "DELETE From arbitres WHERE id_arbitre=?";
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
	public Arbitre update(Arbitre obj) throws DAOException {

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "UPDATE arbitres SET nom=?, prenom=?, sexe=?, id_pays=?, id_niveau=? WHERE id_arbitre=?";  
			preparedSt = connection.prepareStatement(reqSql); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.setString(2, obj.getPrenom());
			preparedSt.setString(3, obj.getSexe());
			preparedSt.setInt(4, obj.getPays().getId());
			preparedSt.setInt(5, obj.getNiveau().getId());
			preparedSt.setInt(6, obj.getId());
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
	public Arbitre find(int id) throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Arbitre arbitre = null;

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null; 

		try {
			String reqSql = "SELECT * From arbitres WHERE id_arbitre=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1,id);
			result = preparedSt.executeQuery(); 
			
			while (result.next()){
				arbitre = new Arbitre();
				DAO<Pays> paysDao = daoFactory.getObjJDBC(Pays.class);
				DAO<NiveauArbitre> niveauDao = daoFactory.getObjJDBC(NiveauArbitre.class);
				arbitre.setNom(result.getString("nom"));
				arbitre.setPrenom(result.getString("prenom"));
				arbitre.setPays(paysDao.find(result.getInt("id_pays")));
				arbitre.setNiveau(niveauDao.find(result.getInt("id_niveau")));
				arbitre.setSexe(result.getString("sexe"));
				arbitre.setId(result.getInt("id_arbitre"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return arbitre;
	}

	@Override
	public Arbitre find(String champs,String param) throws DAOException {
		throw new DAOException("Méthode non implémenté dans la DAO.");
		//return null;
	}

}
