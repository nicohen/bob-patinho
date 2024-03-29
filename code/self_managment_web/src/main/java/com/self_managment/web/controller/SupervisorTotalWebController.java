package com.self_managment.web.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.model.entity.Supervisor;
import com.self_managment.service.SupervisorService;
import com.self_managment.util.DateUtils;
import com.self_managment.web.chart.MetricChart;
import com.self_managment.web.util.JSFUtil;

@Component("supervisorTotalBean")
@Scope("session")
public class SupervisorTotalWebController implements Serializable {
    private static final long serialVersionUID = 1L;
    private int reachedLevelCode = 3;
    private HtmlOutputText reachedLevelOutput;
    private HtmlOutputText metricOutput;
    private HtmlOutputText metricProjectedOutput;
    private String lblSueldoVariable = "Sueldo Variable Proyectado";
    private String lblSueldoTotal = "Sueldo Total Proyectado";
    private Integer metricaCalculadaProy;
    private List<CampaignMetric> metrics;
    private Integer agentsQtty;
    private Supervisor currentSupervisor;
    private List<SelectItem> supervisors;
    private static SupervisorService supervisorService = (SupervisorService) JSFUtil
	    .getBean("supervisorService");
    private Integer imageSize = 0;
    private String currentCampaign;

    public String getCurrentCampaign() {
		return currentSupervisor.getCampaign().getName();
	}

	public void setCurrentCampaign(String currentCampaign) {
		this.currentCampaign = currentCampaign;
	}

	private Date currentPeriod = new Date();

    public void generaGrafico(OutputStream out, Object data) throws IOException {
	if (data == null)
	    return;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	int width = 0;
	int height = 0;
	if (imageSize == 0) {
		width = (int) ((screenSize.width - 60) / getMetrics().size());
		height = (int) (screenSize.height * 0.4);
	} else {
		width = screenSize.width - 60;
		height = (int) (screenSize.height * 0.6);
	}

	MetricChart chart = new MetricChart(null, getCurrentSupervisor()
		.getId(), null, (CampaignMetric) data, getCurrentPeriod());

	ImageIO.write(chart.createBufferedImage(width, height), "jpeg", out);
    }

    public int getRandomParam() {
	return ((int) Math.random() * 100);
    }

    public void periodChange() {
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		.put("period", currentPeriod);

    }

    public Integer getMetricCalculateProy() {

	return metricaCalculadaProy;
    }

    public HtmlOutputText getMetricOutput() {
	return metricOutput;
    }

    public List<SelectItem> getSupervisors() {
	Object obj = SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal();
	if (obj instanceof UserDetails) {
	    for (GrantedAuthority aut : (((UserDetails) obj)).getAuthorities()) {
		if (aut.getAuthority().equalsIgnoreCase("Supervisor")) {
		    supervisors = new ArrayList<SelectItem>();
		    supervisors.add(new SelectItem(getCurrentSupervisor()));
		    break;
		} else if (aut.getAuthority()
			.equalsIgnoreCase("AccountManager")) {
		    supervisors = JSFUtil.getSelectItems(supervisorService
			    .findAll());
		    break;
		}
	    }
	}
	return supervisors;
    }

    public List<CampaignMetric> getMetrics() {
	if (metrics == null) {

	    metrics = getCurrentSupervisor().getCampaign().getCampaignMetric();

	    for (CampaignMetric metric : metrics) {

		    agentsQtty = getCurrentSupervisor().getAgents().size();

			if (!metric.getMetric().isAccumulated())
			    agentsQtty = 1;
	
			metric.setUnsatisfactory(metric.getUnsatisfactory()
				* agentsQtty);
			metric.setMinimum(metric.getMinimum() * agentsQtty);
			metric.setObjective(metric.getObjective() * agentsQtty);
			metric.setOptim(metric.getOptim() * agentsQtty);
	    }
	}

	return metrics;
    }

    private String getStyleForMetricLevel(int level) {
	if (level == 3)
	    return "background-color: green; color: white;";
	if (level == 2)
	    return "background-color: yellow";
	if (level == 1)
	    return "background-color: orange";
	return "background-color: red; color: white;";
    }

    public String getMetricValue() {
	Date dateFrom = DateUtils.getFirstDay(getCurrentPeriod());
	Date dateTo = DateUtils.getLastDay(getCurrentPeriod());

	CampaignMetric metric = (CampaignMetric) metricOutput.getAttributes()
		.get("metric");

	Number metricValue = metric.getMetric().execute(null,
		getCurrentSupervisor().getId(), null,
		DateUtils.getFirstDay(getCurrentPeriod()),
		DateUtils.getLastDay(getCurrentPeriod()));

	int level = metric.getCampaignLevel(metricValue, dateFrom, dateTo);
	
	if(!getShowProjected()) {
		reachedLevelCode = level;
	}
	
	metricOutput.setStyle(getStyleForMetricLevel(level));
	
	NumberFormat nf = NumberFormat.getInstance(Locale.US);
	nf.setMaximumFractionDigits(2);

	return nf.format(metricValue) + " " + metric.getMetric().getUnit();
    }

    public String getProjectedMetricValue() {
	Date dateFrom = DateUtils.getFirstDay(getCurrentPeriod());
	Date dateTo = DateUtils.getLastDay(getCurrentPeriod());

	CampaignMetric metric = (CampaignMetric) metricProjectedOutput
		.getAttributes().get("metric");

	Number metricValue = metric.getMetric().executeProjected(null,
		getCurrentSupervisor().getId(), null,
		DateUtils.getFirstDay(getCurrentPeriod()),
		DateUtils.getLastDay(getCurrentPeriod()));
	
	int level = metric.getCampaignLevelProjected(metricValue, dateFrom, dateTo);
	
	if(getShowProjected()) {
		reachedLevelCode = level;
	}

	metricProjectedOutput.setStyle(getStyleForMetricLevel(level));
	
	NumberFormat nf = NumberFormat.getInstance(Locale.US);
	nf.setMaximumFractionDigits(2);

	return nf.format(metricValue) + " " + metric.getMetric().getUnit();
    }

    public void setMetricOutput(HtmlOutputText metricOutput) {
	this.metricOutput = metricOutput;
    }

    public Date getCurrentPeriod() {
	return currentPeriod;
    }

    public void setCurrentPeriod(Date currentPeriod) {
	this.currentPeriod = currentPeriod;
    }

    public long getTimeStamp() {
	return System.currentTimeMillis();
    }

    public String getLblSueldoVariable() {
	return lblSueldoVariable;
    }

    public String getLblSueldoTotal() {
	return lblSueldoTotal;
    }

    public HtmlOutputText getMetricProjectedOutput() {
	return metricProjectedOutput;
    }

    public void setMetricProjectedOutput(HtmlOutputText metricProjectedOutput) {
	this.metricProjectedOutput = metricProjectedOutput;
    }

    public boolean getShowProjected() {
	return !DateUtils.isOldPeriod(getCurrentPeriod());
    }

    public Supervisor getCurrentSupervisor() {

	if (currentSupervisor == null) {

	    Object obj = SecurityContextHolder.getContext().getAuthentication()
		    .getPrincipal();
	    String username;
	    if (obj instanceof UserDetails) {
		username = ((UserDetails) obj).getUsername();
		for (GrantedAuthority aut : (((UserDetails) obj))
			.getAuthorities()) {
		    if (aut.getAuthority().equalsIgnoreCase("Supervisor")) {
			currentSupervisor = supervisorService
				.findAllByProperty("name", username).get(0);
			break;
		    } else if (aut.getAuthority().equalsIgnoreCase(
			    "AccountManager")) {
			currentSupervisor = supervisorService.findAll().get(0);
			break;
		    }
		}
	    }

	}

	return currentSupervisor;
    }

    public void setCurrentSupervisor(Supervisor currentSupervisor) {
	this.currentSupervisor = currentSupervisor;
    }
    
    public HtmlOutputText getReachedLevelOutput() {
        return reachedLevelOutput;
    }
    
    public void setReachedLevelOutput(HtmlOutputText reachedLevelOutput) {
        this.reachedLevelOutput = reachedLevelOutput;
    }
    
    public String getReachedLevel()
    {
    	reachedLevelOutput.setStyle(getStyleForMetricLevel(reachedLevelCode));
    	if (reachedLevelCode == 3)
    	    return "Optimo";
    	if (reachedLevelCode == 2)
    	    return "Objetivo";
    	if (reachedLevelCode == 1)
    	    return "Minimo";
    	return "No satisfactorio";
    }

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

}
