package com.self_managment.service;


import java.io.Serializable;
import java.util.Date;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.STS;


public interface STSService extends CRUDService<STS, Serializable> {

	public double getOvertimeSalary(Agent agent, int month, int year);
	public long getProductiveHours(Agent agent, int month, int year);
	public Double getScheduledAdherence(Integer campaignId, Integer supervisorId, Integer docket, Date dateFrom, Date dateTo);
}
