package com.app.tennis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtilities {

	/* Quelques méthodes très pratique */
	
	public static void closeSecure(Connection connection){
		if (connection != null){
			try { connection.close(); } catch (SQLException e) {
				 System.out.println( "Erreur lors de la fermeture de la Connection : " + e.getMessage() );
			}
		}
	}
	
	public static void closeSecure(PreparedStatement preparedStatement){
		if (preparedStatement != null){
			try { preparedStatement.close(); } catch (SQLException e) {
				 System.out.println( "Erreur lors de la fermeture du Statement : " + e.getMessage() );
			}
		}
	}
	
	public static void closeSecure(ResultSet resultSet){
		if (resultSet != null){
			try { resultSet.close(); } catch (SQLException e) {
				 System.out.println( "Erreur lors de la fermeture du ResultSet : " + e.getMessage() );
			}
		}
	}
	
	public static void closeSecureAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
		closeSecure(resultSet);
		closeSecure(preparedStatement);
		closeSecure(connection);
	}
	
	public static void closeSecureAll(ResultSet resultSet, PreparedStatement preparedStatement){
		closeSecure(resultSet);
		closeSecure(preparedStatement);
	}
}
