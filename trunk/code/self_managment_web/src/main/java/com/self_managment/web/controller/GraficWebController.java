package com.self_managment.web.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.service.AgentService;
import com.self_managment.service.SupervisorService;
import com.self_managment.service.TTSService;
import com.self_managment.util.DateUtils;
import com.self_managment.web.chart.MetricChart;
import com.self_managment.web.util.JSFUtil;

@Component("graficBean")
@Scope("request")
public class GraficWebController implements Serializable {
    private static final long serialVersionUID = 1L;

    private HtmlOutputText metricOutput;
    private HtmlOutputText metricProjectedOutput;
    private String lblSueldoVariable = "Sueldo Variable Proyectado";
    private String lblSueldoTotal = "Sueldo Total Proyectado";
    private Integer metricaCalculadaProy;
    private List<CampaignMetric> metrics;
    private double sueldoHorasExtra;
    private Agent currentAgent;
    private List<SelectItem> agents;
    private static AgentService agentService = (AgentService) JSFUtil
	    .getBean("agentService");
    private static SupervisorService supervisorService = (SupervisorService) JSFUtil
    .getBean("supervisorService");
    private static TTSService ttsService = (TTSService) JSFUtil
	    .getBean("ttsService");

    private Date currentPeriod;

    public void generaGrafico(OutputStream out, Object data) throws IOException {
	if (data == null)
	    return;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (screenSize.width - 60) / getMetrics().size();
	int height = (int) (screenSize.height * 0.5);

	MetricChart chart = new MetricChart((CampaignMetric) data,
		getCurrentPeriod(), getCurrentAgent());
	ImageIO.write(chart.createBufferedImage(width, height), "jpeg", out);

    }

    public int getRandomParam() {
	return ((int) Math.random() * 100);
    }

    public void periodChange() {
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		.put("period", currentPeriod);
	if (currentPeriod.getYear() == now().getYear()) {
	    if (currentPeriod.getMonth() >= now().getMonth()) {
		lblSueldoVariable = "Sueldo Variable Proyectado";
		lblSueldoTotal = "Sueldo Total Proyectado";
		return;
	    }
	} else if (currentPeriod.getYear() > now().getYear()) {
	    lblSueldoVariable = "Sueldo Variable Proyectado";
	    lblSueldoTotal = "Sueldo Total Proyectado";
	    return;
	}
	lblSueldoVariable = "Sueldo Variable";
	lblSueldoTotal = "Sueldo Total";
    }
    
    public Agent getCurrentAgent() {
	if (FacesContext.getCurrentInstance().getExternalContext()
		.getSessionMap().get("currentAgent") == null) {

	    Object obj = SecurityContextHolder.getContext().getAuthentication()
		    .getPrincipal();
	    String username;
	    if (obj instanceof UserDetails) {
		username = ((UserDetails) obj).getUsername();
		for (GrantedAuthority aut : (((UserDetails) obj))
			.getAuthorities()) {
		    if (aut.getAuthority().equalsIgnoreCase("Agent")) {
			currentAgent = agentService.findAllByProperty("name",
				username).get(0);
			break;
		    } else if (aut.getAuthority()
			    .equalsIgnoreCase("Supervisor")) {
			currentAgent = supervisorService.findAllByProperty(
				"name", username).get(0).getAgents().get(0);
			break;
		    }
		}
	    }

	    FacesContext.getCurrentInstance().getExternalContext()
		    .getSessionMap().put("currentAgent", currentAgent);
	}

	return (Agent) FacesContext.getCurrentInstance().getExternalContext()
		.getSessionMap().get("currentAgent");

    }

    public void setCurrentAgent(Agent selected) {	
	currentAgent = selected;
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	.put("currentAgent", currentAgent);
    }
    
    public List<SelectItem> getAgents() {
	if(agents == null) {
    	Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username;
    	if (obj instanceof UserDetails){
    		for(GrantedAuthority aut : (((UserDetails)obj)).getAuthorities()) {
    			if(aut.getAuthority().equalsIgnoreCase("Agent")) {
    				agents = new ArrayList<SelectItem>();
    				agents.add(new SelectItem(getCurrentAgent()));
    				break;
    			}
    			else if(aut.getAuthority().equalsIgnoreCase("Supervisor")) {
    				username = ((UserDetails)obj).getUsername();
    				agents = JSFUtil.getSelectItems(supervisorService.findAllByProperty("name", username).get(0).getAgents());
    				break;
    			}
    		}
    	}
	}
	return agents;
	}


    public Integer getMetricCalculateProy() {

	return metricaCalculadaProy;
    }

    public HtmlOutputText getMetricOutput() {
	return metricOutput;
    }


    public List<SelectItem> getMetricsCombo() {
	return JSFUtil.getSelectItems(getMetrics());
    }

    public List<CampaignMetric> getMetrics() {
	if (metrics == null) {
	    metrics = getCurrentAgent().getCampaign().getCampaignMetric();
	}
	return metrics;
    }

    private String getStyleForMetricLevel(int level) {
	if (level == 3)
	    return "background-color:green";
	if (level == 2)
	    return "background-color:yellow";
	if (level == 1)
	    return "background-color:orange";
	return "background-color:red";
    }

    public String getMetricValue() {
	Date dateFrom = DateUtils.getFirstDay(getCurrentPeriod());
	Date dateTo = DateUtils.getLastDay(getCurrentPeriod());

	CampaignMetric metric = (CampaignMetric) metricOutput.getAttributes()
		.get("metric");

	metricOutput.setStyle(getStyleForMetricLevel(metric.getLevel(getCurrentAgent(), dateFrom, dateTo)));

	return metric.getMetric().execute(getCurrentAgent(),
		DateUtils.getFirstDay(getCurrentPeriod()),
		DateUtils.getLastDay(getCurrentPeriod())).toString()
		+ " " + metric.getMetric().getUnit();
    }

    public String getProjectedMetricValue() {
	Date dateFrom = DateUtils.getFirstDay(getCurrentPeriod());
	Date dateTo = DateUtils.getLastDay(getCurrentPeriod());

	CampaignMetric metric = (CampaignMetric) metricProjectedOutput.getAttributes()
		.get("metric");

	metricProjectedOutput.setStyle(getStyleForMetricLevel(metric.getLevelProjected(getCurrentAgent(), dateFrom, dateTo)));

	return metric.getMetric().executeProjected(getCurrentAgent(), dateFrom,
		dateTo).toString()
		+ " " + metric.getMetric().getUnit();
    }

    public double getSueldoFijo() {
	return getCurrentAgent().getGrossSalary();
    }

    public double getSueldoVariable() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getCurrentPeriod());
	int month = cal.get(Calendar.MONTH) + 1;
	int year = cal.get(Calendar.YEAR);

	return ttsService.getProductiveHours(getCurrentAgent(), month, year)
		* getCurrentAgent().getHourValue(DateUtils.getFirstDay(month, year),
			DateUtils.getLastDay(month, year));
    }

    private Date now() {
	return Calendar.getInstance().getTime();
    }

    public void setMetricOutput(HtmlOutputText metricOutput) {
	this.metricOutput = metricOutput;
    }

    public double getSueldoHorasExtra() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getCurrentPeriod());
	int month = cal.get(Calendar.MONTH) + 1;
	int year = cal.get(Calendar.YEAR);
	sueldoHorasExtra = ttsService.getOvertimeSalary(getCurrentAgent(), month,
		year);
	NumberFormat nf = NumberFormat.getInstance(Locale.US);
	nf.setMaximumFractionDigits(2);
	double aux = Double.parseDouble(nf.format(sueldoHorasExtra));
	return aux;
    }
    
    public double getValorHoraExtra50() {
	NumberFormat nf = NumberFormat.getInstance(Locale.US);
	nf.setMaximumFractionDigits(2);
	double aux = Double.parseDouble(nf.format(ttsService.getOvertimeValue50(getCurrentAgent())));
	return aux;
    }
    
    public double getValorHoraExtra100() {
	NumberFormat nf = NumberFormat.getInstance(Locale.US);
	nf.setMaximumFractionDigits(2);
	double aux = Double.parseDouble(nf.format(ttsService.getOvertimeValue100(getCurrentAgent())));
	return aux;
    }
    
    public long getHorasExtra50() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getCurrentPeriod());
	int month = cal.get(Calendar.MONTH) + 1;
	int year = cal.get(Calendar.YEAR);
	return ttsService.getExtraHours50Percent(getCurrentAgent(), month, year);
    }
    
    public long getHorasExtra100() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getCurrentPeriod());
	int month = cal.get(Calendar.MONTH) + 1;
	int year = cal.get(Calendar.YEAR);
	return ttsService.getExtraHours100Percent(getCurrentAgent(), month, year);
    }

    public double getSueldoTotal() {
	return getSueldoFijo() + getSueldoHorasExtra() + getSueldoVariable();
    }

    public Date getCurrentPeriod() {
	if (FacesContext.getCurrentInstance().getExternalContext()
		.getSessionMap().get("period") == null) {
	    currentPeriod = new Date();
	    FacesContext.getCurrentInstance().getExternalContext()
		    .getSessionMap().put("period", currentPeriod);
	}

	return (Date) FacesContext.getCurrentInstance().getExternalContext()
		.getSessionMap().get("period");
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

}
