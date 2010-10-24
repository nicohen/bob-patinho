package com.self_managment.model.metric;

import java.util.Date;

import com.self_managment.model.entity.Agent;

public interface MetricStrategy {
	Number execute(Agent agent, Date dateFrom, Date dateTo);
}
