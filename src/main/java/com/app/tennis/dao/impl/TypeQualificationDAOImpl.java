package com.app.tennis.dao.impl;

import javax.persistence.EntityManager;

import com.app.tennis.dao.TypeQualificationDAO;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.exceptions.DAOException;

public class TypeQualificationDAOImpl extends ObjDAOImpl<TypeQualification> implements TypeQualificationDAO {

	public TypeQualificationDAOImpl(EntityManager connection) throws DAOException {
		super(connection, TypeQualification.class);
	}

}
