package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.Agent;
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
    public Long getAmountOfTimeInAnAvailableCallStatus(Agent agent,
	    Date dateFrom, Date dateTo) {

	List<Long> result = getHibernateTemplate()
		.find(
			"select sum(inCall) from Summary where pk.agent=? and pk.date between ? and ?",
			new Object[] { agent, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Double getAverageTalkTime(Agent agent, Date dateFrom, Date dateTo) {
	List<Double> result = getHibernateTemplate()
		.find(
			"select avg(inCall*60/quantityOfCalls) from Summary where pk.agent=? and pk.date between ? and ?",
			new Object[] { agent, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0D;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long getNCH(Agent agent, Date dateFrom, Date dateTo) {
	List<Long> result = getHibernateTemplate()
		.find(
			"select sum(quantityOfCalls) from Summary where pk.agent=? and pk.date between ? and ?",
			new Object[] { agent, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Long getTransferPCT(Agent agent, Date dateFrom, Date dateTo) {
	List<Long> result = getHibernateTemplate()
		.find(
			"select (sum(transferredCalls)/sum(quantityOfCalls))*100 from Summary where pk.agent=? and pk.date between ? and ?",
			new Object[] { agent, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Long getTotalLoggedTime(Agent agent, Date dateFrom, Date dateTo) {
	List<Long> result = getHibernateTemplate()
		.find(
			"select sum(loggeado) from Summary where pk.agent=? and pk.date between ? and ?",
			new Object[] { agent, dateFrom, dateTo });

	return result.get(0) != null ? result.get(0) : 0L;
    }

}