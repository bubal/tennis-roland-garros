package com.app.tennis.services.impl;

import com.app.tennis.dao.TypeQualificationDAO;
import com.app.tennis.dao.impl.TypeQualificationDAOImpl;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.services.TypeQualificationService;

public class TypeQualificationServiceImpl extends ObjServiceImpl<TypeQualification> implements TypeQualificationService {

	@Override
	public TypeQualificationDAO getDAO() {
		return new TypeQualificationDAOImpl();
	}
}
