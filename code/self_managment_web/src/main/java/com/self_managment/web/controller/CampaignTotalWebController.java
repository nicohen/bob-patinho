package com.self_managment.web.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Campaign;
import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.service.CampaignService;
import com.self_managment.util.DateUtils;
import com.self_managment.web.chart.MetricChart;
import com.self_managment.web.util.JSFUtil;

@Component("campaignTotalBean")
@Scope("session")
public class CampaignTotalWebController implements Serializable {
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
    private Campaign currentCampaign;
    private List<SelectItem> campaigns;
    private static CampaignService campaignService = (CampaignService) JSFUtil
	    .getBean("campaignService");

    private Date currentPeriod = new Date();

    public void generaGrafico(OutputStream out, Object data) throws IOException {
	if (data == null)
	    return;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (screenSize.width - 60) / getMetrics().size();
	int height = (int) (screenSize.height * 0.4);

	MetricChart chart = new MetricChart(getCurrentCampaign().getId(), null,
		null, (CampaignMetric) data, getCurrentPeriod());

	ImageIO.write(chart.createBufferedImage(width, height), "jpeg", out);
    }

    public int getRandomParam() {
	return ((int) Math.random() * 100);
    }

    public void periodChange() {
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		.put("period", currentPeriod);
    }
    
    public void campaignChanged(ValueChangeEvent event) {
	metrics = null;
    }

    public Integer getMetricCalculateProy() {

	return metricaCalculadaProy;
    }

    public HtmlOutputText getMetricOutput() {
	return metricOutput;
    }

    public List<SelectItem> getCampaigns() {
	if (campaigns == null || "true".equals(JSFUtil.getRequestParameter("refresh")))
	    campaigns = JSFUtil.getSelectItems(campaignService.findAll());

	return campaigns;
    }

    @SuppressWarnings("unchecked")
    public List<CampaignMetric> getMetrics() {
	if (metrics == null) {

	    agentsQtty = getCurrentCampaign().getAgents().size();
	    metrics = getCurrentCampaign().getCampaignMetric();

	    for (CampaignMetric metric : metrics) {

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

	Number metricValue = metric.getMetric().execute(
		getCurrentCampaign().getId(), null, null,
		DateUtils.getFirstDay(getCurrentPeriod()),
		DateUtils.getLastDay(getCurrentPeriod()));

	int level = metric.getCampaignLevel(metricValue, dateFrom, dateTo);
	
	if((!getShowProjected())&&(level < reachedLevelCode))
		reachedLevelCode = level;
	
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

	Number metricValue = metric.getMetric().executeProjected(
		getCurrentCampaign().getId(), null, null,
		DateUtils.getFirstDay(getCurrentPeriod()),
		DateUtils.getLastDay(getCurrentPeriod()));
	
	int level = metric.getCampaignLevelProjected(metricValue, dateFrom, dateTo);

	if((getShowProjected())&&(level < reachedLevelCode))
		reachedLevelCode = level;
	
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

    public Campaign getCurrentCampaign() {
	if (currentCampaign == null)
	    currentCampaign = campaignService.findAll().get(0);

	return currentCampaign;
    }

    public void setCurrentCampaign(Campaign currentCampaign) {
	this.currentCampaign = currentCampaign;
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

}
