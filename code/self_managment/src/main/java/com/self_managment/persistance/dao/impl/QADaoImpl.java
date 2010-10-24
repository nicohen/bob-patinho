package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.QA;
import com.self_managment.persistance.dao.QADao;

@Repository("qaDao")
public class QADaoImpl extends GenericDaoImpl<QA, Serializable> implements
	QADao {

    @Override
    protected Class<QA> getEntityClass() {
	return QA.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Number sumPossiblePoints(Integer docket, Date dateFrom, Date dateTo) {

	List<Long> result = getHibernateTemplate()
		.find(
			"select sum(posiblePointsQuantity) from QA where pk.agent.docket=? and pk.date between ? and ?",
			new Object[] { docket, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Number sumQAMonitors(Integer docket, Date dateFrom, Date dateTo) {

	List<Long> result = getHibernateTemplate()
		.find(
			"select sum(evaluationsQuantity) from QA where pk.agent.docket=? and pk.date between ? and ?",
			new Object[] { docket, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Number sumAchievedPoints(Integer docket, Date dateFrom, Date dateTo) {

	List<Long> result = getHibernateTemplate()
		.find(
			"select sum(achievedPointsQuantity) from QA where pk.agent.docket=? and pk.date between ? and ?",
			new Object[] { docket, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;

    }

}
