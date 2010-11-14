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
    public Number sumPossiblePoints(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {

	String query = getQuery("sum(qa.posiblePointsQuantity)", campaignId, supervisorId, docket);
	Integer id = getId(campaignId, supervisorId, docket);

	List<Long> result = getHibernateTemplate().find(query,
		new Object[] { id, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Number sumQAMonitors(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {

	String query = getQuery("sum(qa.evaluationsQuantity)", campaignId, supervisorId, docket);
	Integer id = getId(campaignId, supervisorId, docket);

	List<Long> result = getHibernateTemplate().find(query,
		new Object[] { id, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Number sumAchievedPoints(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {

	String query = getQuery("sum(qa.achievedPointsQuantity)", campaignId, supervisorId, docket);
	Integer id = getId(campaignId, supervisorId, docket);

	List<Long> result = getHibernateTemplate().find(query,
		new Object[] { id, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;

    }
    
    private Integer getId(Integer campaignId,
	    Integer supervisorId, Integer docket) {
	if (campaignId != null) {
	    return campaignId;
	} else if (supervisorId != null) {
	    return supervisorId;
	}
	return docket;
    }

    private String getQuery(String selection, Integer campaignId,
	    Integer supervisorId, Integer docket) {
	String query = "select " + selection + " from QA as qa "
		+ "inner join qa.pk.agent as ag "
		+ "inner join ag.supervisor as sup ";

	if (campaignId != null) {
	    query += "where sup.campaign.id = ? ";
	} else if (supervisorId != null) {
	    query += "where sup.id = ? ";
	} else {
	    query += "where ag.docket = ? ";
	}

	return query += "and qa.pk.date between ? and ?";
    }

}
