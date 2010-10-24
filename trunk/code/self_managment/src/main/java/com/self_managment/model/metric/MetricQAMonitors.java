package com.self_managment.model.metric;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Agent;
import com.self_managment.service.QAService;

@Component("QA_MONITORS")
public class MetricQAMonitors implements MetricStrategy {
    @Autowired
    private QAService qaService;

    @Override
    public Number execute(Agent agent, Date dateFrom, Date dateTo) {
	return qaService.sumQAMonitors(agent.getDocket(), dateFrom, dateTo);
    }

}
