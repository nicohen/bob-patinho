package com.self_managment.model.metric;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self_managment.service.STSService;

@Component("SCHED_ADG")
public class MetricScheduleAdherence implements MetricStrategy {
	@Autowired
	private STSService stsService;

	@Override
	public Number execute(Integer campaignId, Integer supervisorId,
			Integer docket, Date dateFrom, Date dateTo) {
		return stsService.getScheduledAdherence(campaignId, supervisorId,
				docket, dateFrom, dateTo);
	}

}
