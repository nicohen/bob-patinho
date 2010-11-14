package com.self_managment.model.metric;

import java.util.Date;

public interface MetricStrategy {
	Number execute(Integer campaignId, Integer supervisorId, Integer docket, Date dateFrom, Date dateTo);
}
