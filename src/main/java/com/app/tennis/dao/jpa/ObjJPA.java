package com.app.tennis.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.app.tennis.dao.DAO;
import com.app.tennis.exceptions.DAOException;

public class ObjJPA<T> implements DAO<T> {
	
	private EntityManager connection;
	final Class<T> typeClass;
	
	public ObjJPA(EntityManager connection, Class<T> typeClass) throws DAOException {
		this.connection = connection;
		this.typeClass = typeClass;
	}

	@Override
	public T create(T obj) throws DAOException {
		connection.getTransaction().begin();
		connection.persist(obj);
		connection.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(int id) throws DAOException {	
		connection.getTransaction().begin();
		connection.remove(connection.find(typeClass,id));
		connection.getTransaction().commit();
		
	}

	@Override
	public T find(int id) throws DAOException {
		return connection.find(typeClass,id);
	}

	@Override
	public T find(String champs, String param) throws DAOException {
		String[] nameObj = typeClass.getName().split("\\.");
		TypedQuery<T> query;
		try {
			query = connection.createNamedQuery(nameObj[nameObj.length-1]+".findObj", typeClass).setParameter(champs, param);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public T update(T obj) throws DAOException {
		connection.getTransaction().begin();
		obj = connection.merge(obj);
		connection.getTransaction().commit();
		return obj;
	}

	@Override
	public List<T> listAll() throws DAOException {
		List<T> listeObj = new ArrayList<T>();
		String[] nameObj = typeClass.getName().split("\\.");
		TypedQuery<T> query = connection.createNamedQuery(nameObj[nameObj.length-1]+".findAll", typeClass);
		listeObj = query.getResultList();
		return listeObj;
	}

}
