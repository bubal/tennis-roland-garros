package com.app.tennis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tennis.data.TypeQualification;
import com.app.tennis.repository.TypeQualificationRepository;
import com.app.tennis.services.TypeQualificationService;

public class TypeQualificationServiceImpl extends ObjServiceImpl<TypeQualification> implements TypeQualificationService {

	@Autowired
	private TypeQualificationRepository objRepository;
	
	@Override
	public JpaRepository<TypeQualification, Integer> getRepository() {
		return this.objRepository;
	}

}
