package com.self_managment.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.service.AgentService;
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

    private static AgentService agentService = (AgentService) JSFUtil
	    .getBean("agentService");
    private static TTSService ttsService = (TTSService) JSFUtil
	    .getBean("ttsService");

    private Agent currentAgent = agentService.findById(100);

    private Date currentPeriod;

    public void generaGrafico(OutputStream out, Object data) throws IOException {
	if (data == null)
	    return;

	MetricChart chart = new MetricChart((CampaignMetric) data,
		getCurrentPeriod(), currentAgent);
	ImageIO.write(chart.createBufferedImage(900, 375), "jpeg", out);

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
	return currentAgent;
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
	    metrics = currentAgent.getCampaign().getCampaignMetric();
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

	metricOutput.setStyle(getStyleForMetricLevel(metric.getLevel(currentAgent, dateFrom, dateTo)));

	return metric.getMetric().execute(currentAgent,
		DateUtils.getFirstDay(getCurrentPeriod()),
		DateUtils.getLastDay(getCurrentPeriod())).toString()
		+ " " + metric.getMetric().getUnit();
    }

    public String getProjectedMetricValue() {
	Date dateFrom = DateUtils.getFirstDay(getCurrentPeriod());
	Date dateTo = DateUtils.getLastDay(getCurrentPeriod());

	CampaignMetric metric = (CampaignMetric) metricProjectedOutput.getAttributes()
		.get("metric");

	metricProjectedOutput.setStyle(getStyleForMetricLevel(metric.getLevelProjected(currentAgent, dateFrom, dateTo)));

	return metric.getMetric().executeProjected(currentAgent, dateFrom,
		dateTo).toString()
		+ " " + metric.getMetric().getUnit();
    }

    public double getSueldoFijo() {
	return currentAgent.getGrossSalary();
    }

    public double getSueldoVariable() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getCurrentPeriod());
	int month = cal.get(Calendar.MONTH) + 1;
	int year = cal.get(Calendar.YEAR);

	return ttsService.getProductiveHours(currentAgent, month, year)
		* currentAgent.getHourValue(DateUtils.getFirstDay(month, year),
			DateUtils.getLastDay(month, year));
    }

    private Date now() {
	return Calendar.getInstance().getTime();
    }

    public void setCurrentAgent(Agent currentAgent) {
	this.currentAgent = currentAgent;
    }

    public void setMetricOutput(HtmlOutputText metricOutput) {
	this.metricOutput = metricOutput;
    }

    public double getSueldoHorasExtra() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getCurrentPeriod());
	int month = cal.get(Calendar.MONTH) + 1;
	int year = cal.get(Calendar.YEAR);
	sueldoHorasExtra = ttsService.getOvertimeSalary(currentAgent, month,
		year);
	NumberFormat nf = NumberFormat.getInstance(Locale.US);
	nf.setMaximumFractionDigits(2);
	double aux = Double.parseDouble(nf.format(sueldoHorasExtra));
	return aux;
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
