package com.self_managment.model.metric;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Agent;
import com.self_managment.service.AgentService;
import com.self_managment.service.SummaryService;
import com.self_managment.service.TTSService;

@Component("AUX_TM")
public class MetricTimeInAuxStatus implements MetricStrategy {
    @Autowired
    private SummaryService summaryService;
    @Autowired
    private AgentService agentService;

    @Autowired
    private TTSService ttService;

    @Override
    public Number execute(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(dateFrom);
	
	List<Agent> agents;
	if (campaignId != null) {
	    agents = agentService.findAllByProperty("campaign.id", campaignId);
	} else if (supervisorId != null) {
	    agents = agentService.findAllByProperty("supervisor.id", supervisorId);
	} else {
	    Agent agent = new Agent();
	    agent.setDocket(docket);
	    agents = new ArrayList<Agent>();
	    agents.add(agent);
	}
	
	long productiveHours = 0;
	for (Agent a : agents)
	    productiveHours += ttService.getProductiveHours(a, dateFrom, dateTo);

	return productiveHours * 60 - summaryService.getTotalLoggedTime(campaignId, supervisorId,
			docket, dateFrom, dateTo);

    }

}
