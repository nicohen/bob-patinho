package com.self_managment.model.metric;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Agent;
import com.self_managment.service.SummaryService;

@Component("NCH")
public class MetricNCH implements MetricStrategy {
    @Autowired
    private SummaryService summaryService;

    @Override
    public Number execute(Agent agent, Date dateFrom, Date dateTo) {
	return summaryService.getNCH(agent, dateFrom, dateTo);
    }

}