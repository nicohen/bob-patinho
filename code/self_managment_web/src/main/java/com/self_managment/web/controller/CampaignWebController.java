package com.self_managment.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.Campaign;
import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.model.entity.Metric;
import com.self_managment.service.CampaignService;
import com.self_managment.service.MetricService;
import com.self_managment.web.util.JSFUtil;

@Component("campaignBean")
@Scope("session")
public class CampaignWebController {
    private List<Campaign> campaigns;
    private Campaign campaign = new Campaign();
    private CampaignService service;
    private MetricService metricService;
    private Agent agent;
    private CampaignMetric campaignMetric;
    private List<SelectItem> metrics;
    private boolean canAddMetric;

    @SuppressWarnings("unchecked")
    public CampaignWebController() {
	ApplicationContext ctx = FacesContextUtils
		.getWebApplicationContext(FacesContext.getCurrentInstance());

	service = (CampaignService) ctx.getBean("campaignService");

	metricService = (MetricService) ctx.getBean("metricService");
    }

    public List<SelectItem> getMetrics() {
	if (metrics == null) {
	    metrics = JSFUtil.getSelectItems(metricService.findAll());
	}
	return metrics;
    }

    public List<Campaign> getCampaigns() {
	if (campaigns == null)
	    campaigns = service.findAll();
	return campaigns;
    }

    public Campaign getCampaign() {
	return campaign;
    }

    public void setCampaign(Campaign campaign) {
	this.campaign = campaign;
    }

    public Agent getAgent() {
	return agent;
    }

    public void setAgent(Agent agent) {
	this.agent = agent;
    }

    public String update() {
	try {
	    service.saveOrUpdate(campaign);
	    campaigns = service.findAll();
	    setCampaign(new Campaign());
	} catch (Exception e) {
	    e.printStackTrace();
	    JSFUtil.addErrorMessage("Error");
	}
	return "";
    }

    public String refreshCampaign() {
	campaign = service.findById(campaign.getId());
	if (campaign == null) {
	    setCampaign(new Campaign());
	    JSFUtil.addErrorMessage("Campania inexistente");
	}
	return "";
    }

    public String create() {
	setCampaign(new Campaign());
	return "";
    }

    public String createAgent() {
	setAgent(new Agent());
	return "";
    }

    public String createMetric() {
	CampaignMetric campaignMetric = new CampaignMetric();
	campaignMetric.setMetric(new Metric());
	campaignMetric.setCampaign(campaign);
	setCampaignMetric(campaignMetric);
	return "";
    }

    public String addMetric() {
	if (campaign.getCampaignMetric() == null) {
	    List<CampaignMetric> metrics = new ArrayList<CampaignMetric>();
	    metrics.add(campaignMetric);
	    campaign.setCampaignMetric(metrics);
	} else {
	    if (!campaign.getCampaignMetric().contains(campaignMetric))
		campaign.getCampaignMetric().add(campaignMetric);
	}
	return "";
    }

    public String addAgent() {
	if (campaign.getAgents() == null) {
	    List<Agent> agents = new ArrayList<Agent>();
	    agents.add(agent);
	    campaign.setAgents(agents);
	} else {
	    campaign.getAgents().add(agent);
	}
	return "";
    }

    public String deleteMetric() {
	campaign.getCampaignMetric().remove(campaignMetric);
	return "";
    }

    public String deleteAgent() {
	campaign.getAgents().remove(agent);
	return "";
    }

    public String delete() {
	try {
	    service.delete(campaign);
	} catch (Exception e) {
	    JSFUtil.addErrorMessage("Error");
	    e.printStackTrace();
	} finally {
	    campaigns = service.findAll();
	}

	return "";
    }

    public CampaignMetric getCampaignMetric() {
	return campaignMetric;
    }

    public void setCampaignMetric(CampaignMetric campaignMetric) {
	this.campaignMetric = campaignMetric;
    }

    public boolean isCanAddMetric() {
	return (campaign != null && campaign.getCampaignMetric() != null && campaign
		.getCampaignMetric().size() == 3) ? true : false;
    }
}
