package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.Supervisor;
import com.self_managment.persistance.dao.SupervisorDao;
import com.self_managment.util.DateUtils;

@Repository("supervisorDao")
public class SupervisorDaoImpl extends GenericDaoImpl<Supervisor, Serializable>
	implements SupervisorDao {

    @Override
    protected Class<Supervisor> getEntityClass() {
	return Supervisor.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Supervisor> findAllSupervisorsWithoutCampaign() {
	return getHibernateTemplate()
		.find(
			"select s from Supervisor s " +
			"left join s.campaign c " +
			"where c.id is null or c.endDate < ?",
			DateUtils.getFirstDay(new Date()));

	// return
	// this.getSession().createCriteria(Supervisor.class).add(Expression.isNull("campaign")).list();
    }

}
