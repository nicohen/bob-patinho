package com.self_managment.model.metric;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Agent;
import com.self_managment.service.SummaryService;
import com.self_managment.service.TTSService;

@Component("AUX_TM")
public class MetricTimeInAuxStatus implements MetricStrategy {
    @Autowired
    private SummaryService summaryService;

    @Autowired
    private TTSService ttService;

    @Override
    public Number execute(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(dateFrom);
	// TODO ver de cambiar la firma de getProductiveHours
	// out hour - in hour
	Agent agent = new Agent();
	agent.setDocket(docket);
	long productiveHours = ttService.getProductiveHours(agent, cal
		.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));

	return productiveHours * 60 - summaryService.getTotalLoggedTime(campaignId, supervisorId,
			docket, dateFrom, dateTo);

    }

}
