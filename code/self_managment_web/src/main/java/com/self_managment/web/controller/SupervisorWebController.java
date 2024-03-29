package com.self_managment.web.controller;

import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Supervisor;
import com.self_managment.service.CampaignService;
import com.self_managment.service.SupervisorService;
import com.self_managment.web.util.JSFUtil;

@Component("supervisorBean")
@Scope("session")
public class SupervisorWebController {
	private List<Supervisor> supervisors;
	private List<SelectItem> campaigns;
	private Supervisor supervisor = new Supervisor();
	private SupervisorService service;
	private CampaignService campaignService;
	private boolean editMode;

	public SupervisorWebController() {
		service = (SupervisorService) JSFUtil.getBean("supervisorService");
		campaignService = (CampaignService) JSFUtil.getBean("campaignService");
	}

	public void onload() {
		supervisors = service.findAll();
	}

	public List<Supervisor> getSupervisors() {
		String refresh = StringUtils.trimToEmpty(JSFUtil
				.getRequestParameter("refresh"));
		if (supervisors == null || "true".equals(refresh))
			supervisors = service.findAll();
		return supervisors;
	}

	public List<SelectItem> getCampaigns() {
		String refresh = StringUtils.trimToEmpty(JSFUtil
				.getRequestParameter("refresh"));
		if (campaigns == null || "true".equals(refresh))
			campaigns = JSFUtil.getSelectItems(campaignService.findAll());
		return campaigns;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public String update() {
		try {
			if (!editMode)
				service.save(supervisor);
			else
				service.update(supervisor);
			setSupervisor(new Supervisor());
		} catch (Exception e) {
			JSFUtil.addErrorMessage("Error");
		} finally {
			supervisors = service.findAll();
		}
		return "";
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public String create() {
		editMode = false;
		setSupervisor(new Supervisor());
		return "";
	}

	public String delete() {
		try {
			service.delete(supervisor);
		} catch (Exception e) {
			JSFUtil.addErrorMessage("Error");
		} finally {
			supervisors = service.findAll();
		}
		return "";
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode() {
		this.editMode = true;
	}

}
