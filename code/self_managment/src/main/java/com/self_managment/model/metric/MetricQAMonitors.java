package com.self_managment.model.metric;

import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Agent;

@Component("metricQAMonitors")
public class MetricQAMonitors implements MetricStrategy {

	@Override
	public Double execute(Agent agent) {
		// TODO Auto-generated method stub
		return (double)0;
	}

	@Override
	public String getMetricCode() {
		return "QA_MONITORS";
	}


}
