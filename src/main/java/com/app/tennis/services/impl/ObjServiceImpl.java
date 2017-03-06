package com.app.tennis.services.impl;

import java.util.List;

import com.app.tennis.dao.DAO;
import com.app.tennis.services.ObjService;

public abstract class ObjServiceImpl<T> implements ObjService<T> {

	public abstract DAO<T> getDAO();
	
	@Override
	public T create(T obj) {
		return getDAO().create(obj);
	}

	@Override
	public void deleteById(int id) {
		getDAO().deleteById(id);
	}

	@Override
	public T findById(int id) {
		return getDAO().findById(id);
	}

	@Override
	public T update(T obj) {
		return getDAO().update(obj);
	}

	@Override
	public List<T> listAll() {
		return getDAO().listAll();
	}

}
