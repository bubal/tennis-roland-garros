package com.app.tennis.dao.jdbc;

import static com.app.tennis.dao.DAOUtilities.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOFactory;
import com.app.tennis.data.Joueur;
import com.app.tennis.data.Pays;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.exceptions.DAOException;

public class JoueurDAO implements DAO<Joueur>{

	private DAOFactory  daoFactory;

	public JoueurDAO() {
		super();
	}

	public JoueurDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Joueur> listAll() throws DAOException{

		List<Joueur> listeJoueurs = new ArrayList<Joueur>();

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;  /* On utilise PrepareStatement pour plus de sécurité et de performance */

		try {
			String reqSql = "SELECT * FROM joueurs";  /* preparation de la requête */
			preparedSt = connection.prepareStatement(reqSql);  
			result = preparedSt.executeQuery(); 

			while (result.next()){
				Joueur joueur = new Joueur();
				DAO<Pays> paysDao = daoFactory.getObjJDBC(Pays.class);
				DAO<TypeQualification> qualificationDao = daoFactory.getObjJDBC(TypeQualification.class);
				joueur.setNom(result.getString("nom"));
				joueur.setPrenom(result.getString("prenom"));
				joueur.setPays(paysDao.find(result.getInt("id_pays"))); 
				joueur.setQualification(qualificationDao.find(result.getInt("id_qualification")));
				joueur.setSexe(result.getString("sexe"));
				joueur.setClassement(result.getInt("classement"));
				joueur.setId(result.getInt("id_joueur"));
				listeJoueurs.add(joueur);
			}
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return listeJoueurs;
	}

	@Override
	public Joueur create(Joueur obj) throws DAOException{

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {

			String reqSql = "INSERT INTO joueurs(nom,prenom,sexe,id_pays,classement,id_qualification) VALUES(?,?,?,?,?,?)";  
			preparedSt = connection.prepareStatement(reqSql , Statement.RETURN_GENERATED_KEYS); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.setString(2, obj.getPrenom());
			preparedSt.setString(3, obj.getSexe());
			preparedSt.setInt(4, obj.getPays().getId());
			preparedSt.setInt(5, obj.getClassement());
			preparedSt.setInt(6,obj.getQualification().getId());
			preparedSt.executeUpdate();

			ResultSet rsKey = preparedSt.getGeneratedKeys();
			if (rsKey.next()) {
				obj.setId(rsKey.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un joueur." );
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
			String reqSql = "DELETE From joueurs WHERE id_joueur=?";
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
	public Joueur update(Joueur obj) throws DAOException{

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null;

		try {
			String reqSql = "UPDATE joueurs SET nom=?, prenom=?, sexe=?, id_pays=?, classement=?, id_qualification=? WHERE id_joueur=?";  
			preparedSt = connection.prepareStatement(reqSql); 
			preparedSt.setString(1, obj.getNom());
			preparedSt.setString(2, obj.getPrenom());
			preparedSt.setString(3, obj.getSexe());
			preparedSt.setInt(4, obj.getPays().getId());
			preparedSt.setInt(5, obj.getClassement());
			preparedSt.setInt(6, obj.getQualification().getId());
			preparedSt.setInt(7, obj.getId());
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
	public Joueur find(int id) throws DAOException{

		Joueur joueur = null;

		Connection connection = daoFactory.getConnection();
		ResultSet result = null;
		PreparedStatement preparedSt = null; 

		try {
			String reqSql = "SELECT * From joueurs WHERE id_joueur=?";
			preparedSt = connection.prepareStatement(reqSql);
			preparedSt.setInt(1,id);
			result = preparedSt.executeQuery(); 
			while (result.next()){
				joueur = new Joueur();
				DAO<Pays> paysDao = daoFactory.getObjJDBC(Pays.class);
				DAO<TypeQualification> qualificationDao = daoFactory.getObjJDBC(TypeQualification.class);
				joueur.setNom(result.getString("nom"));
				joueur.setPrenom(result.getString("prenom"));
				joueur.setPays(paysDao.find(result.getInt("id_pays")));
				joueur.setQualification(qualificationDao.find(result.getInt("id_qualification")));
				joueur.setSexe(result.getString("sexe"));
				joueur.setClassement(result.getInt("classement"));
				joueur.setId(result.getInt("id_joueur"));
			}
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new DAOException(e);
		}
		finally{
			closeSecureAll(result,preparedSt,connection);
		}
		return joueur;

	}

	@Override
	public Joueur find(String champs,String param) throws DAOException {
		throw new DAOException("Méthode non implémenté dans la DAO.");
		//return null;
	}

}
