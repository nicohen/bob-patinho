package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.Calendar;
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
    public Number sumPossiblePoints(Integer docket) {
		Calendar cal = Calendar.getInstance();
		Date today = new Date();
		cal.setTime(today);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		List<Long> result = getHibernateTemplate().find(
    		"select sum(posible_points_quantity) from qa where docket=? and date between ? and ?",
    		new Object[] { docket, cal.getTime(), today });
	    
		return result.get(0) != null ? result.get(0) : 0L;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Number sumQAMonitors(Integer docket) {
		Calendar cal = Calendar.getInstance();
		Date today = new Date();
		cal.setTime(today);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
	
		List<Long> result = getHibernateTemplate().find(
			"select count(1) from qa where docket=? and date between ? and ?", 
			new Object[] { docket, cal.getTime(), today });
	    
		return result.get(0) != null ? result.get(0) : 0L;

    }

    @SuppressWarnings("unchecked")
    @Override
	public Number sumAchievedPoints(Integer docket) {
		Calendar cal = Calendar.getInstance();
		Date today = new Date();
		cal.setTime(today);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
	
		List<Long> result = getHibernateTemplate().find(
			"select sum(achieved_points_quantity) from qa where docket=? and date between ? and ?",
			new Object[] { docket, cal.getTime(), today });
	    
		return result.get(0) != null ? result.get(0) : 0L;

    }

}
