package com.self_managment.persistance.dao;

import java.io.Serializable;
import java.util.Date;

import com.self_managment.model.entity.QA;

public interface QADao extends GenericDao<QA, Serializable> {
	
	public Number sumPossiblePoints(Integer campaignId, Integer supervisorId, Integer docket, Date dateFrom, Date dateTo);

	public Number sumAchievedPoints(Integer campaignId, Integer supervisorId, Integer docket, Date dateFrom, Date dateTo);

	public Number sumQAMonitors(Integer campaignId, Integer supervisorId, Integer docket, Date dateFrom, Date dateTo);
}
