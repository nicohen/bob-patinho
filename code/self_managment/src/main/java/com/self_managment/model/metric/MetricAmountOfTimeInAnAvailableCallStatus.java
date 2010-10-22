package com.self_managment.model.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Agent;
import com.self_managment.service.SummaryService;

@Component("AVAIL_TM")
public class MetricAmountOfTimeInAnAvailableCallStatus implements
		MetricStrategy {
	@Autowired
	private SummaryService summaryService;

	@Override
	public Number execute(Agent agent) {
		return summaryService.getAmountOfTimeInAnAvailableCallStatus(agent);
	}

	@Override
	public String getMetricCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
