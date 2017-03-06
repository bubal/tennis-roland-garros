package com.app.tennis.dao.impl;

import com.app.tennis.dao.TypeQualificationDAO;
import com.app.tennis.data.TypeQualification;

public class TypeQualificationDAOImpl extends ObjDAOImpl<TypeQualification> implements TypeQualificationDAO {

	@Override
	Class<TypeQualification> getTypeClass() {
		return TypeQualification.class;
	}
}
