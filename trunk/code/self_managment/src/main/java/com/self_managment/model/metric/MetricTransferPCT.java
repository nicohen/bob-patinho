package com.self_managment.model.metric;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self_managment.service.SummaryService;

@Component("TPCT")
public class MetricTransferPCT implements MetricStrategy {
    @Autowired
    private SummaryService summaryService;

    @Override
    public Number execute(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	return summaryService.getTransferPCT(campaignId, supervisorId, docket,
		dateFrom, dateTo);
    }

}