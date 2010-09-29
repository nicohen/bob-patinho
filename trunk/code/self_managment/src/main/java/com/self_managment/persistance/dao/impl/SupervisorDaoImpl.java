package com.self_managment.persistance.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.Supervisor;
import com.self_managment.persistance.dao.SupervisorDao;

@Repository("supervisorDao")
public class SupervisorDaoImpl extends GenericDaoImpl<Supervisor, Serializable>
		implements SupervisorDao {

	@Override
	protected Class<Supervisor> getEntityClass() {
		return Supervisor.class;
	}

}
