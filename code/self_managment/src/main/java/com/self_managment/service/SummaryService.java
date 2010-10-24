package com.self_managment.service;

import java.io.Serializable;
import java.util.Date;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.Summary;

public interface SummaryService extends CRUDService<Summary, Serializable> {
	public Long getAmountOfTimeInAnAvailableCallStatus(Agent agent, Date dateFrom, Date dateTo);
	public Double getAverageTalkTime(Agent agent, Date dateFrom, Date dateTo);

}