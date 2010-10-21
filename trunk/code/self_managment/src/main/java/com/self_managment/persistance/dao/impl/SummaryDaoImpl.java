package com.self_managment.persistance.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


import com.self_managment.model.entity.Summary;

import com.self_managment.persistance.dao.SummaryDao;


@Repository("summaryDao")
public class SummaryDaoImpl extends GenericDaoImpl<Summary, Serializable> implements
		SummaryDao {

	@Override
	protected Class<Summary> getEntityClass() {
		return Summary.class;
	}
	


}