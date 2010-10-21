package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.Calendar;
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
	public Long getAmountOfTimeInAnAvailableCallStatus(Agent agent) {
		Calendar cal = Calendar.getInstance();
		Date today = new Date();
		cal.setTime(today);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		List<Long> result = getHibernateTemplate()
				.find(
						"select sum(inCall) from Summary where pk.agent=? and pk.date between ? and ?",
						new Object[] { agent, cal.getTime(), today });

		return result.get(0) != null ? result.get(0) : 0L;

	}

}