package com.self_managment.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.component.html.HtmlOutputText;
import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.service.AgentService;
import com.self_managment.service.MetricService;
import com.self_managment.service.QAService;
import com.self_managment.service.TTSService;
import com.self_managment.util.DateUtils;
import com.self_managment.web.util.JSFUtil;

@Component("graficBean")
@Scope("request")
public class GraficWebController {
    public Integer metric1_optim = 80;
    public Integer metric1_objetivo = 50;
    public Integer metric1_minimo = 20;
    public Integer metric1_noSatisfactorio = 10;
    public Integer metric2_optim = 50;
    public Integer metric2_objetivo = 35;
    public Integer metric2_minimo = 15;
    public Integer metric2_noSatisfactorio = 5;
    public Integer metric3_optim = 35;
    public Integer metric3_objetivo = 20;
    public Integer metric3_minimo = 10;
    public Integer metric3_noSatisfactorio = 5;

    public double value_metrics_optim = 20.93;
    public double value_metrics_objetivo = 15.65;
    public double value_metrics_minimo = 10.95;
    public double value_metrics_noSatisfactorio = 0.00;

    public Integer metric1_values = 89;
    public Integer metric2_values = 47;
    public Integer metric3_values = 32;

    public Integer cantHoras = 108;

    private HtmlOutputText metricOutput;
    private String today = now();
    private double sueldoFijo;
    private double sueldoVariableProyectado;
    private Integer metricaCalculadaProy;
    private List<CampaignMetric> metrics;
    private String currentCampaign;
    private double sueldoHorasExtra;
    private double sueldoTotalProyectado;
    private String agentName;

    private static AgentService agentService = (AgentService) JSFUtil.getBean("agentService");
    private static QAService qaService = (QAService) JSFUtil.getBean("qaService");
    private static MetricService metricService = (MetricService) JSFUtil
	    .getBean("metricService");
    private static TTSService ttsService = (TTSService) JSFUtil.getBean("ttsService");
    private Agent currentAgent = agentService.findById(100);

    private org.jfree.data.time.TimeSeries pop = new org.jfree.data.time.TimeSeries(
	    "Puntos Acumulados");
    private org.jfree.data.time.TimeSeries pop1 = new org.jfree.data.time.TimeSeries(
    "Optimo");
    private org.jfree.data.time.TimeSeries pop2 = new org.jfree.data.time.TimeSeries(
    "Objetivo");
    private org.jfree.data.time.TimeSeries pop3 = new org.jfree.data.time.TimeSeries(
    "Minimo");
    private org.jfree.data.time.TimeSeries pop4 = new org.jfree.data.time.TimeSeries(
    "No Satisfactorio");
    private org.jfree.data.time.TimeSeries pop5 = new org.jfree.data.time.TimeSeries(
    "Tendencia");

    private Integer CantDias = 0;
    
    private Date currentPeriod = new Date();

    public void generaGrafico(OutputStream out, Object data) throws IOException {

    


	pop.add(new Day(1, 10, 2010), 0);
	pop.add(new Day(2, 10, 2010), 200);
	pop.add(new Day(3, 10, 2010), 333);
	pop.add(new Day(4, 10, 2010), 400);
	pop.add(new Day(5, 10, 2010), 780);
	pop.add(new Day(6, 10, 2010), 800);
	pop.add(new Day(7, 10, 2010), 900);
	pop.add(new Day(8, 10, 2010), 1450);
	pop.add(new Day(9, 10, 2010), 1450);
	pop.add(new Day(10, 10, 2010), 1470);
	pop.add(new Day(11, 10, 2010), 1800);
	pop.add(new Day(12, 10, 2010), 2000);
	pop.add(new Day(13, 10, 2010), 2000);
	pop.add(new Day(14, 10, 2010), 2000);
	pop.add(new Day(15, 10, 2010), 2020);
	pop.add(new Day(16, 10, 2010), 2100);
	pop.add(new Day(17, 10, 2010), 2150);
	pop.add(new Day(18, 10, 2010), 2300);
	pop.add(new Day(19, 10, 2010), 2500);
	pop.add(new Day(20, 10, 2010), 2550);
	pop.add(new Day(21, 10, 2010), 2560);
	pop.add(new Day(22, 10, 2010), 2590);
	pop.add(new Day(23, 10, 2010), 2700);
	pop.add(new Day(24, 10, 2010), 2900);
	pop.add(new Day(25, 10, 2010), 3000);
	pop.add(new Day(26, 10, 2010), 3100);
	pop.add(new Day(27, 10, 2010), 3200);
	pop.add(new Day(28, 10, 2010), 3400);
	pop.add(new Day(29, 10, 2010), 3500);
	pop.add(new Day(30, 10, 2010), 3500);
	
	pop1.add(new Day(1, 10, 2010), 3000);	
	pop1.add(new Day(30, 10, 2010), 3000);
	
	pop2.add(new Day(1, 10, 2010), 2000);	
	pop2.add(new Day(30, 10, 2010), 2000);
	
	pop3.add(new Day(1, 10, 2010), 1000);	
	pop3.add(new Day(30, 10, 2010), 1000);
	
	pop4.add(new Day(1, 10, 2010), 500);	
	pop4.add(new Day(30, 10, 2010), 500);

	TimeSeriesCollection dataset = new TimeSeriesCollection();
	dataset.addSeries(pop);
	dataset.addSeries(pop1);
	dataset.addSeries(pop2);
	dataset.addSeries(pop3);
	dataset.addSeries(pop4);

	JFreeChart chart1 = ChartFactory.createTimeSeriesChart("Tendencia",
		"Dias", "Proyeccion Metrica", dataset, true, true, false);

	BufferedImage buffImg = chart1.createBufferedImage(500, // width
		375, // height
		BufferedImage.TYPE_INT_RGB, // image type
		null);

	ImageIO.write(buffImg, "jpeg", out);

    }

    public Agent getCurrentAgent() {
	return currentAgent;
    }

    public Integer getMetric1_optim() {
	return metric1_optim;
    }

    public Integer getMetricCalculateProy() {
	// metricaCalculadaProy = (30 - CantDias) * sumaTotal;
	return metricaCalculadaProy;
    }

    public HtmlOutputText getMetricOutput() {
	return metricOutput;
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
	Date dateFrom = DateUtils.getFirstDay(currentPeriod);
	Date dateTo = DateUtils.getLastDay(currentPeriod);

	CampaignMetric metric = (CampaignMetric) metricOutput.getAttributes()
		.get("metric");
	
	metricOutput.setStyle(getStyleForMetricLevel(metric.getLevel(currentAgent, dateFrom, dateTo)));
	
	return metric.getMetric().execute(currentAgent,
		DateUtils.getFirstDay(currentPeriod), DateUtils.getLastDay(currentPeriod))
		.toString() + " " + metric.getMetric().getUnit();
    }

    public double getSueldoFijo() {
	return currentAgent.getGrossSalary();
    }

    public double getSueldoProyectado() {
	sueldoVariableProyectado = sueldoProyectado();
	return sueldoVariableProyectado;
    }

    public double getSueldoVariableProyectado() {
	return sueldoVariableProyectado;
    }

    public String getToday() {
	return today;
    }

    private String now() {
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	return sdf.format(Calendar.getInstance().getTime());
    }

    public void setCurrentAgent(Agent currentAgent) {
	this.currentAgent = currentAgent;
    }

    public void setMetricOutput(HtmlOutputText metricOutput) {
	this.metricOutput = metricOutput;
    }

    public void setSueldoFijo(double sueldoFijo) {
	this.sueldoFijo = sueldoFijo;
    }

    public void setSueldoVariableProyectado(double sueldoVariableProyectado) {
	this.sueldoVariableProyectado = sueldoVariableProyectado;
    }

    public double sueldoALaFecha() {
	if (metric1_values > metric1_optim && metric1_values > metric2_optim
		&& metric1_values > metric3_optim)
	    sueldoFijo = cantHoras * value_metrics_optim;
	return sueldoFijo;
    }

    public double sueldoProyectado() {
	double proyectado_metric1;
	double proyectado_metric2;
	double proyectado_metric3;

	// List<QA> qas = qaService.sumPossiblePoints("1");
	// qas.get(0).getPk().getAgent().getGrossSalary()

	proyectado_metric1 = (160 * metric1_values) / cantHoras;
	proyectado_metric2 = (160 * metric2_values) / cantHoras;
	proyectado_metric3 = (160 * metric3_values) / cantHoras;

	if (proyectado_metric1 > metric1_optim
		&& proyectado_metric2 > metric2_optim
		&& proyectado_metric3 > metric3_optim)
	    sueldoVariableProyectado = 160 * value_metrics_optim;
	return sueldoVariableProyectado;
    }

    public void setAgentName(String agentName) {
	this.agentName = agentName;
    }

    public String getAgentName() {
	return currentAgent.getName();
    }

    public double getSueldoHorasExtra() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(currentPeriod);
	int month = cal.get(Calendar.MONTH) + 1;
	int year = cal.get(Calendar.YEAR);
	
	sueldoHorasExtra = ttsService
		.getOvertimeSalary(currentAgent, month, year);
	return sueldoHorasExtra;
    }

    public double getSueldoTotalProyectado() {
	return sueldoFijo + getSueldoHorasExtra() + sueldoVariableProyectado;
    }

    public Double getVariableSalary() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(currentPeriod);
	int month = cal.get(Calendar.MONTH) + 1;
	int year = cal.get(Calendar.YEAR);
	
	return ttsService.getProductiveHours(currentAgent, month , year)
		* currentAgent.getHourValue(DateUtils.getFirstDay(month, year),
			DateUtils.getLastDay(month, year));
    }

    public Date getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(Date currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

}
