package com.self_managment.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.Campaign;
import com.self_managment.service.CRUDService;
import com.self_managment.web.util.JSFUtil;

@Component("campaignBean")
@Scope("session")
public class CampaignWebController {
    private List<Campaign> campaigns;
    private Campaign campaign = new Campaign();
    private CRUDService<Campaign, Serializable> service;
    private Agent agent;

    @SuppressWarnings("unchecked")
    public CampaignWebController() {
	ApplicationContext ctx = FacesContextUtils
		.getWebApplicationContext(FacesContext.getCurrentInstance());

	service = (CRUDService<Campaign, Serializable>) ctx
		.getBean("campaignService");
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
	    JSFUtil.addErrorMessage("Campaña inexistente");
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
}
