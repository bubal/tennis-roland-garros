package com.app.tennis.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.app.tennis.exceptions.DAOException;

public interface DAO<T> {

	public abstract T create(T obj) throws DAOException;

	public abstract void delete(int id) throws DAOException;
	
	public abstract T find(int id) throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public abstract T find(String champs, String param) throws DAOException;

	public abstract T update(T obj) throws DAOException;
	
	public abstract List<T> listAll() throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
}

