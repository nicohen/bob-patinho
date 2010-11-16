package com.self_managment.persistance.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.self_managment.model.entity.STS;


public interface STSDao extends GenericDao<STS, Serializable> {
	
	public List<STS> findByAgentMonthYear(Integer docket, Integer month, Integer year);
	/**
	 * Time in minutes
	 * @param campaignId
	 * @param supervisorId
	 * @param docket
	 * @param dateFrom
	 * @param dateTo
	 * @return Time in minutes
	 */
	public Double getScheduledAdherence(final Integer campaignId, final Integer supervisorId, final Integer docket, final Date dateFrom, final Date dateTo);

}
