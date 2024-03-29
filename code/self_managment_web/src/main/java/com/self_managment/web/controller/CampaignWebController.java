package com.self_managment.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.self_managment.model.entity.Campaign;
import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.model.entity.Metric;
import com.self_managment.model.entity.Supervisor;
import com.self_managment.service.CampaignService;
import com.self_managment.service.MetricService;
import com.self_managment.service.SupervisorService;
import com.self_managment.util.DateUtils;
import com.self_managment.web.util.JSFUtil;
import com.sun.faces.util.MessageUtils;

@Component("campaignBean")
@Scope("session")
public class CampaignWebController {
    private List<Campaign> campaigns;
    private Campaign campaign = new Campaign();
    private CampaignService service;
    private MetricService metricService;
    private SupervisorService supervisorService;
    private Supervisor supervisor;
    private List<Supervisor> deletedSupervisors = new ArrayList<Supervisor>();
    private List<SelectItem> freeSupervisors;
    private Metric metric;
    private CampaignMetric campaignMetric;
    private List<SelectItem> metrics;
    private boolean editMode;

    public CampaignWebController() {
	ApplicationContext ctx = FacesContextUtils
		.getWebApplicationContext(FacesContext.getCurrentInstance());

	service = (CampaignService) ctx.getBean("campaignService");

	metricService = (MetricService) ctx.getBean("metricService");

	supervisorService = (SupervisorService) ctx
		.getBean("supervisorService");
    }

    public void onload() {
	campaigns = service.findAll();
    }

    public String addSupervisor() {
	supervisor.setCampaign(campaign);
	if (campaign.getSupervisors() == null) {
	    List<Supervisor> supervisors = new ArrayList<Supervisor>();
	    supervisors.add(supervisor);
	    campaign.setSupervisors(supervisors);
	} else
	    campaign.getSupervisors().add(supervisor);
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

    public void changeMetric(ValueChangeEvent event) {
	setMetric((Metric) event.getNewValue());
    }

    public String create() {
	setEditMode(false);
	Campaign campaign = new Campaign();
	campaign.setStartDate(new Date());
	campaign.setEndDate(getCurrentNextMonth());
	setCampaign(campaign);
	deletedSupervisors.clear();
	return "";
    }

    public String createSupervisor() {
	setSupervisor(new Supervisor());
	supervisor.setCampaign(campaign);
	return "";
    }

    public String createMetric() {
	CampaignMetric campaignMetric = new CampaignMetric();
	campaignMetric.setMetric(new Metric());
	campaignMetric.setCampaign(campaign);
	setCampaignMetric(campaignMetric);
	this.setMetric(null);
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

    public String deleteSupervisor() {
	campaign.getSupervisors().remove(supervisor);
	supervisor.setCampaign(null);
	deletedSupervisors.add(supervisor);

	return "";
    }

    public String deleteMetric() {
	campaign.getCampaignMetric().remove(campaignMetric);
	return "";
    }

    public Supervisor getSupervisor() {
	return supervisor;
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

    public List<SelectItem> getFreeSupervisors() {
	// if(freeSupervisors == null)
	freeSupervisors = JSFUtil.getSelectItems(supervisorService
		.findAllSupervisorsWithoutCampaign());
	return freeSupervisors;
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
	deletedSupervisors.clear();
	return "";
    }

    public void setSupervisor(Supervisor supervisor) {
	this.supervisor = supervisor;
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

    public boolean validateCampaignValues() {
	if (campaign.getOptimValue() >= campaign.getObjetiveValue()
		&& campaign.getObjetiveValue() >= campaign.getMinimumValue()
		&& campaign.getMinimumValue() >= campaign
			.getUnsatisfactoryValue())
		if (DateUtils.getFirstDay(campaign.getStartDate()).before(DateUtils.getFirstDay(campaign.getEndDate())))
			return false;
		else
		{
			JSFUtil.addErrorMessage(MessageUtils.getExceptionMessage(
		    "error.metric.dates").getSummary());
			return true;
		}
	else {
		if (DateUtils.getFirstDay(campaign.getStartDate()).before(DateUtils.getFirstDay(campaign.getEndDate())))
		{
		    JSFUtil.addErrorMessage(MessageUtils.getExceptionMessage(
		    "error.metric.values").getSummary());
		    JSFUtil.addErrorMessage(MessageUtils.getExceptionMessage(
		    "error.metric.dates").getSummary());
		    return true;
		}
		else
		{
			JSFUtil.addErrorMessage(MessageUtils.getExceptionMessage(
		    "error.metric.values").getSummary());
			return true;
		}
	}

    }

    private boolean campaignWithoutMetrics() {
	if (campaign.getCampaignMetric() == null
		|| campaign.getCampaignMetric().isEmpty()) {
	    JSFUtil.addErrorMessage(MessageUtils.getExceptionMessage(
		    "error.metric.nometric").getSummary());
	    return true;
	}
	return false;
    }

    private boolean validateCampaign() {
	boolean error = validateCampaignValues();

	return campaignWithoutMetrics() || error;
    }

    public String update() {
	try {
	    if (validateCampaign())
		return "";

	    if (editMode)
		service.update(campaign);
	    else
		service.save(campaign);

	    for (Supervisor supervisor : deletedSupervisors) {
		supervisorService.update(supervisor);
	    }

	    campaigns = service.findAll();

	    setCampaign(new Campaign());

	} catch (Exception e) {
	    JSFUtil.addErrorMessage("Error");
	}
	return "";
    }

    public void validateMetricOptim(FacesContext context, UIComponent component,
	    Object input) throws ValidatorException {
	Double var = (Double) input;

	if (metric != null && metric.getOptimMinValue() != null
		&& metric.getOptimMaxValue() != null)
	    if (var < metric.getOptimMinValue() || var > metric.getOptimMaxValue())
		throw new ValidatorException(MessageUtils.getExceptionMessage(
			"error.metric.range", metric.getOptimMinValue(), metric
				.getOptimMaxValue()));
    }
    
    public void validateMetricObjetive(FacesContext context, UIComponent component,
    	    Object input) throws ValidatorException {
    	Double var = (Double) input;

    	if (metric != null && metric.getObjetiveMinValue() != null
    		&& metric.getObjetiveMaxValue() != null)
    	    if (var < metric.getObjetiveMinValue() || var > metric.getObjetiveMaxValue())
    		throw new ValidatorException(MessageUtils.getExceptionMessage(
    			"error.metric.range", metric.getObjetiveMinValue(), metric
    				.getObjetiveMaxValue()));
    }
    
    public void validateMetricMinimum(FacesContext context, UIComponent component,
    	    Object input) throws ValidatorException {
    	Double var = (Double) input;

    	if (metric != null && metric.getMinimumMinValue() != null
    		&& metric.getMinimumMaxValue() != null)
    	    if (var < metric.getMinimumMinValue() || var > metric.getMinimumMaxValue())
    		throw new ValidatorException(MessageUtils.getExceptionMessage(
    			"error.metric.range", metric.getMinimumMinValue(), metric
    				.getMinimumMaxValue()));
    }
    
    public void validateMetricUnsatisfactory(FacesContext context, UIComponent component,
    	    Object input) throws ValidatorException {
    	Double var = (Double) input;

    	if (metric != null && metric.getUnsatisfactoryMinValue() != null
    		&& metric.getUnsatisfactoryMaxValue() != null)
    	    if (var < metric.getUnsatisfactoryMinValue() || var > metric.getUnsatisfactoryMaxValue())
    		throw new ValidatorException(MessageUtils.getExceptionMessage(
    			"error.metric.range", metric.getUnsatisfactoryMinValue(), metric
    				.getUnsatisfactoryMaxValue()));
    }
    
    public Date getCurrentNextMonth()
    {
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MONTH, 1);
    	return cal.getTime();
    }
    
    public void setCurrentNextMonth(Date value)
    {
    }
    
}
