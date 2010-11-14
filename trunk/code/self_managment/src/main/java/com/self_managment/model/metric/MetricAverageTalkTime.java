package com.self_managment.model.metric;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self_managment.service.SummaryService;

@Component("AVG_TALK_TM")
public class MetricAverageTalkTime implements MetricStrategy {
    @Autowired
    private SummaryService summaryService;

    @Override
    public Number execute(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	return summaryService.getAverageTalkTime(campaignId, supervisorId,
		docket, dateFrom, dateTo);
    }

}
