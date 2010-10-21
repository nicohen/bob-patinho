package com.self_managment.service;

import java.io.Serializable;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.Summary;

public interface SummaryService extends CRUDService<Summary, Serializable> {
	public Long getAmountOfTimeInAnAvailableCallStatus(Agent agent);

}