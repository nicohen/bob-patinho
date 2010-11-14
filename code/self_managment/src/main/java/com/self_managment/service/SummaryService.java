package com.self_managment.service;

import java.io.Serializable;
import java.util.Date;

import com.self_managment.model.entity.Summary;

public interface SummaryService extends CRUDService<Summary, Serializable> {
    /**
     * Time in minutes.
     * 
     * @param campaignId
     * @param supervisorId
     * @param docket
     * @param dateFrom
     * @param dateTo
     * @return Time in minutes.
     */
    public Long getAmountOfTimeInAnAvailableCallStatus(Integer campaignId,
	    Integer supervisorId, Integer docket, Date dateFrom, Date dateTo);

    public Double getAverageTalkTime(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo);

    /**
     * Time in minutes.
     * @param campaignId
     * @param supervisorId
     * @param docket
     * @param dateFrom
     * @param dateTo
     * @return Time in minutes.
     */
    public Long getTotalLoggedTime(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo);

    public Long getNCH(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo);

    public Long getTransferPCT(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo);

}