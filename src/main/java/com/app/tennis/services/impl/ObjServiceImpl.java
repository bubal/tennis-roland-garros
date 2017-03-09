package com.app.tennis.services.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.services.ObjService;

public abstract class ObjServiceImpl<T> implements ObjService<T> {

	public abstract JpaRepository<T,Integer> getRepository();
	
	@Override
	public T create(T obj) {
		return getRepository().save(obj);
	}

	@Override
	public void deleteById(int id) {
		getRepository().delete(id);
	}

	@Override
	public T findById(int id) {
		return getRepository().findOne(id);
	}

	@Override
	public T update(T obj) {
		return getRepository().save(obj);
	}

	@Override
	public List<T> listAll() {
		return getRepository().findAll();
	}

}
