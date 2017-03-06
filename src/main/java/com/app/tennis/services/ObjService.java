package com.app.tennis.services;

import java.util.List;

public interface ObjService<T> {

	T create(T obj);
	void deleteById(int id);
	T findById(int id);
	T update(T obj);
	List<T> listAll();
	
	
	
}
