package com.self_managment.persistance.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.QA;
import com.self_managment.persistance.dao.QADao;

@Repository("qaDao")
public class QADaoImpl extends GenericDaoImpl<QA, Serializable> implements QADao {

	@Override
	protected Class<QA> getEntityClass() {
		return QA.class;
	}
	
	public Double sumPossiblePoints(Integer docket) {
		//select sum(puntos_posibles) from qa where legajo=? and fecha between ? and ? // fecha desde = primer día del mes, fecha hasta = hoy
		return (Double)getHibernateTemplate().find("select sum(posible_points_quantity) from qa where docket=?",docket).get(0);
	}
	
	public Double sumQAMonitors(Integer docket) {
		//select count(1) from qa where legajo=? and fecha between ? and ? // fecha desde = primer día del mes, fecha hasta = hoy
		return (Double)getHibernateTemplate().find("select count(1) from qa where docket=?",docket).get(0);
	}

	public Number sumAchievedPoints(Integer docket) {
		//select sum(achieved_points_quantity) from qa where legajo=? and fecha between ? and ? // fecha desde = primer día del mes, fecha hasta = hoy
		return (Double)getHibernateTemplate().find("select sum(achieved_points_quantity) from qa where docket=?",docket).get(0);
	}
}
