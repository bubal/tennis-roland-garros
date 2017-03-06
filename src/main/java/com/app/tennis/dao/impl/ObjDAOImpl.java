package com.app.tennis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.app.tennis.dao.DAO;
import com.app.tennis.dao.DAOUtilities;

public abstract class ObjDAOImpl<T> implements DAO<T> {
	
	public EntityManager connection = DAOUtilities.getEntityManager();
	abstract Class<T> getTypeClass();

	@Override
	public T create(T obj) {
		connection.getTransaction().begin();
		connection.persist(obj);
		connection.getTransaction().commit();
		return obj;
	}

	@Override
	public void deleteById(int id) {	
		connection.getTransaction().begin();
		connection.remove(connection.find(getTypeClass(),id));
		connection.getTransaction().commit();
		
	}

	@Override
	public T findById(int id) {
		return connection.find(getTypeClass(),id);
	}

	@Override
	public T update(T obj) {
		connection.getTransaction().begin();
		obj = connection.merge(obj);
		connection.getTransaction().commit();
		return obj;
	}

	@Override
	public List<T> listAll() {
		List<T> listeObj = new ArrayList<T>();
		String[] nameObj = getTypeClass().getName().split("\\.");
		TypedQuery<T> query = connection.createNamedQuery(nameObj[nameObj.length-1]+".findAll", getTypeClass());
		listeObj = query.getResultList();
		return listeObj;
	}

}
