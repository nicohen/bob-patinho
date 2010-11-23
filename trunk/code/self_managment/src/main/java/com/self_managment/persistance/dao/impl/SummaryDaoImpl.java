package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.Summary;
import com.self_managment.persistance.dao.SummaryDao;

@Repository("summaryDao")
public class SummaryDaoImpl extends GenericDaoImpl<Summary, Serializable>
	implements SummaryDao {

    @Override
    protected Class<Summary> getEntityClass() {
	return Summary.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long getAmountOfTimeInAnAvailableCallStatus(Integer campaignId,
	    Integer supervisorId, Integer docket, Date dateFrom, Date dateTo) {

	String query = getQuery("sum(summary.inCall)", campaignId, supervisorId, docket);
	Integer id = getId(campaignId, supervisorId, docket);

	List<Long> result = getHibernateTemplate().find(query,
		new Object[] { id, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Double getAverageTalkTime(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {

	String query = getQuery("avg(summary.inCall*60/summary.quantityOfCalls)", campaignId,
		supervisorId, docket);
	Integer id = getId(campaignId, supervisorId, docket);

	List<Double> result = getHibernateTemplate().find(query,
		new Object[] { id, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0D;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long getNCH(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {

	String query = getQuery("sum(summary.quantityOfCalls)", campaignId,
		supervisorId, docket);
	Integer id = getId(campaignId, supervisorId, docket);

	List<Long> result = getHibernateTemplate().find(query,
		new Object[] { id, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Double getTransferPCT(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {

	String query = getQuery(
		"(sum(summary.transferredCalls)/sum(summary.quantityOfCalls))*100.00", campaignId,
		supervisorId, docket);
	Integer id = getId(campaignId, supervisorId, docket);
	
	List<Number> result = getHibernateTemplate().find(query,
		new Object[] { id, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0).doubleValue() : 0D;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long getTotalLoggedTime(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {

	String query = getQuery("sum(summary.loggeado)", campaignId, supervisorId,
		docket);
	Integer id = getId(campaignId, supervisorId, docket);

	List<Long> result = getHibernateTemplate().find(query,
		new Object[] { id, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;
    }

    private Integer getId(Integer campaignId, Integer supervisorId,
	    Integer docket) {
	if (campaignId != null) {
	    return campaignId;
	} else if (supervisorId != null) {
	    return supervisorId;
	}
	return docket;
    }

    private String getQuery(String selection, Integer campaignId,
	    Integer supervisorId, Integer docket) {
	String query = "select " + selection + " from Summary as summary "
		+ "inner join summary.pk.agent as ag "
		+ "inner join ag.supervisor as sup ";

	if (campaignId != null) {
	    query += "where sup.campaign.id = ? ";
	} else if (supervisorId != null) {
	    query += "where sup.id = ? ";
	} else {
	    query += "where ag.docket = ? ";
	}

	return query += "and summary.pk.date between ? and ?";
    }

}