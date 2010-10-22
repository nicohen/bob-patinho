package com.self_managment.service;

import java.io.Serializable;

import com.self_managment.model.entity.QA;

public interface QAService extends CRUDService<QA, Serializable> {

	public Double sumPossiblePoints(Integer docket);
}
