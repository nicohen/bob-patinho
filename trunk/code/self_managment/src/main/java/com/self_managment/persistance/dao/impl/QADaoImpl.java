package com.self_managment.persistance.dao.impl;

import java.io.Serializable;

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
	
	public Long sumPossiblePoints(String docket) {
		return (Long)getHibernateTemplate().find("select sum(posible_points_quantity) from qa where docket=?",docket).get(0);
	}

}
