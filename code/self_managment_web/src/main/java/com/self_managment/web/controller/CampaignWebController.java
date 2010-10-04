package com.self_managment.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

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
import com.sun.faces.util.MessageUtils;

@Component("campaignBean")
@Scope("session")
public class CampaignWebController {
    private List<Campaign> campaigns;
    private Campaign campaign = new Campaign();
    private CampaignService service;
    private MetricService metricService;
    private Agent agent;
    private Metric metric;
    private CampaignMetric campaignMetric;
    private List<SelectItem> metrics;
    private boolean canAddMetric;
    private boolean editMode;

    @SuppressWarnings("unchecked")
    public CampaignWebController() {
	ApplicationContext ctx = FacesContextUtils
		.getWebApplicationContext(FacesContext.getCurrentInstance());

	service = (CampaignService) ctx.getBean("campaignService");

	metricService = (MetricService) ctx.getBean("metricService");
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

    public void change(ValueChangeEvent event) {
	setMetric((Metric) event.getNewValue());
    }

    public String create() {
	setEditMode(false);
	setCampaign(new Campaign());
	return "";
    }

    public String createAgent() {
	setAgent(new Agent());
	return "";
    }

    public String createMetric() {
	this.setMetric(null);
	CampaignMetric campaignMetric = new CampaignMetric();
	campaignMetric.setMetric(new Metric());
	campaignMetric.setCampaign(campaign);
	setCampaignMetric(campaignMetric);
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

    public String deleteAgent() {
	campaign.getAgents().remove(agent);
	return "";
    }

    public String deleteMetric() {
	campaign.getCampaignMetric().remove(campaignMetric);
	return "";
    }

    public Agent getAgent() {
	return agent;
    }

    public Campaign getCampaign() {
	return campaign;
    }

    public CampaignMetric getCampaignMetric() {
	return campaignMetric;
    }

    public List<Campaign> getCampaigns() {
	if (campaigns == null)
	    campaigns = service.findAll();
	return campaigns;
    }

    public Metric getMetric() {
	return metric;
    }

    public List<SelectItem> getMetrics() {
	if (metrics == null) {
	    metrics = JSFUtil.getSelectItems(metricService.findAll());
	}
	return metrics;
    }

    public boolean isCanAddMetric() {
	return (campaign != null && campaign.getCampaignMetric() != null && campaign
		.getCampaignMetric().size() == 3) ? true : false;
    }

    public boolean isEditMode() {
	return editMode;
    }

    public String refreshCampaign() {
	setEditMode(true);
	campaign = service.findById(campaign.getId());
	if (campaign == null) {
	    setCampaign(new Campaign());
	    JSFUtil.addErrorMessage("Campania inexistente");
	}
	return "";
    }

    public void setAgent(Agent agent) {
	this.agent = agent;
    }

    public void setCampaign(Campaign campaign) {
	this.campaign = campaign;
    }

    public void setCampaignMetric(CampaignMetric campaignMetric) {
	this.campaignMetric = campaignMetric;
	this.metric = campaignMetric.getMetric();
    }

    public void setEditMode(boolean editMode) {
	this.editMode = editMode;
    }

    public void setMetric(Metric metric) {
	this.metric = metric;
    }

    public String update() {
	try {
		if (campaign.getCampaignMetric().size() >= 1){
		    service.saveOrUpdate(campaign);
		    campaigns = service.findAll();
		    setCampaign(new Campaign());
			}
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    JSFUtil.addErrorMessage("Error");
	}
	return "";
    }

    public void validateMetric(FacesContext context, UIComponent component,
	    Object input) throws ValidatorException {
	Double var = (Double) input;

	if (metric != null && metric.getMinValue() != null
		&& metric.getMaxValue() != null)
	    if (var < metric.getMinValue() || var > metric.getMaxValue())
		throw new ValidatorException(MessageUtils.getExceptionMessage(
			"error.metric.range", metric.getMinValue(), metric
				.getMaxValue()));
    }
}
