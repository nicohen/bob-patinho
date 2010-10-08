package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Expression;
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
	@Override
	public List<Supervisor> findAllSupervisorsWithoutCampaign()
	{
		return this.getSession().createCriteria(Supervisor.class).add(Expression.isNull("campaign")).list();
	}

}
