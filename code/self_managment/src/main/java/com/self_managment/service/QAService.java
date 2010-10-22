package com.self_managment.service;

import java.io.Serializable;

import com.self_managment.model.entity.QA;

public interface QAService extends CRUDService<QA, Serializable> {

	public Number sumPossiblePoints(Integer docket);
	
	public Number sumQAMonitors(Integer docket);

	public Number sumAchievedPoints(Integer docket);
}
