package com.self_managment.persistance.dao;

import java.io.Serializable;

import com.self_managment.model.entity.QA;

public interface QADao extends GenericDao<QA, Serializable> {
	
	public Number sumPossiblePoints(Integer docket);

	public Number sumAchievedPoints(Integer docket);

	public Number sumQAMonitors(Integer docket);
}
