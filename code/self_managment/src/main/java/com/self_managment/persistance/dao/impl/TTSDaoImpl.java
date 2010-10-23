package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import com.self_managment.model.entity.TTS;
import com.self_managment.persistance.dao.TTSDao;

@Repository("ttsDao")
public class TTSDaoImpl extends GenericDaoImpl<TTS, Serializable> implements
		TTSDao {

	@Override
	protected Class<TTS> getEntityClass() {
		return TTS.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<TTS> findByAgentMonthYear(Integer docket, Integer month, Integer year) {
		@SuppressWarnings("deprecation")
		Calendar lowerLimit = Calendar.getInstance();
		Calendar topLimit = Calendar.getInstance();
		lowerLimit.set(year, month-1, 1, 0, 0, 0);
		topLimit.set(year, month, 1, 0, 0, 0);
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("pk.agent.docket", docket));
		criteria.add(Restrictions.lt("pk.date", topLimit.getTime()));
		criteria.add(Restrictions.ge("bulgingDate", lowerLimit.getTime()));
		return getHibernateTemplate().findByCriteria(criteria);
	}

}