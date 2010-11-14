package com.self_managment.persistance.dao;

import java.io.Serializable;
import java.util.Date;

import com.self_managment.model.entity.Summary;

public interface SummaryDao extends GenericDao<Summary, Serializable> {
    public Long getAmountOfTimeInAnAvailableCallStatus(Integer campaignId,
	    Integer supervisorId, Integer docket, Date dateFrom, Date dateTo);

    public Double getAverageTalkTime(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo);

    public Long getNCH(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo);

    public Long getTransferPCT(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo);

    public Long getTotalLoggedTime(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo);
}
