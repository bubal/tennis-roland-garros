package com.app.tennis.dao;

import java.util.List;

public interface DAO<T> {

	T create(T obj);
	void deleteById(int id);
	T findById(int id);
	T update(T obj);
	List<T> listAll();
	
}

