package com.self_managment.model.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Agent;
import com.self_managment.service.QAService;

@Component("metricQAPossiblePoints")
public class MetricQAPossiblePoints implements MetricStrategy {
	@Autowired
	private QAService qaService;

	@Override
	public Double execute(Agent agent) {
		// return qaService.getPossiblePoints(agent);
		return 2.0;
	}

}
