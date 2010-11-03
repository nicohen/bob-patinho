package com.self_managment.persistance.dao;

import java.io.Serializable;
import java.util.Date;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.Summary;

public interface SummaryDao extends GenericDao<Summary, Serializable> {
    public Long getAmountOfTimeInAnAvailableCallStatus(Agent agent,
	    Date dateFrom, Date dateTo);

    public Double getAverageTalkTime(Agent agent, Date dateFrom, Date dateTo);
    public Double getNCH(Agent agent, Date dateFrom, Date dateTo);
    public Double getTransferPCT(Agent agent, Date dateFrom, Date dateTo);
    
    public Long getTotalLoggedTime(Agent agent, Date dateFrom, Date dateTo);
}
