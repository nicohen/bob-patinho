package com.self_managment.model.metric;

import com.self_managment.model.entity.Agent;

public interface MetricStrategy {
	Double execute(Agent agent);
}
