package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.STS;

import com.self_managment.persistance.dao.STSDao;

import com.self_managment.util.DateUtils;

@Repository("stsDao")
public class STSDaoImpl extends GenericDaoImpl<STS, Serializable> implements
	STSDao {

    @Override
    protected Class<STS> getEntityClass() {
	return STS.class;
    }

    @SuppressWarnings("unchecked")
    public List<STS> findByAgentMonthYear(Integer docket, Integer month,
	    Integer year) {

	return getHibernateTemplate().find(
		"from STS where pk.agent.docket=? and bulgingDate > ? and pk.date <= ?",
		new Object[] { docket, DateUtils.getFirstDay(month, year),
			DateUtils.getLastDay(month, year) });
    }

}