package com.self_managment.service;

import java.io.Serializable;
import java.util.Date;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.Summary;

public interface SummaryService extends CRUDService<Summary, Serializable> {
    	/**
    	 * Total time in minutes.
    	 * @param agent
    	 * @param dateFrom
    	 * @param dateTo
    	 * @return Total time in minutes.
    	 */
	public Long getAmountOfTimeInAnAvailableCallStatus(Agent agent, Date dateFrom, Date dateTo);
	public Double getAverageTalkTime(Agent agent, Date dateFrom, Date dateTo);
	/**
	 * Total time in minutes for an agent.
	 * @param agent
	 * @param dateFrom
	 * @param dateTo
	 * @return total time in minutes.
	 */
	public Long getTotalLoggedTime(Agent agent, Date dateFrom, Date dateTo);
	public Double getNCH(Agent agent, Date dateFrom, Date dateTo);
	public Double getTransferPCT(Agent agent, Date dateFrom, Date dateTo);
	
}