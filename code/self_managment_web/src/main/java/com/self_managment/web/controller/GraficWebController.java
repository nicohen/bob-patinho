package com.self_managment.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.service.AgentService;
import com.self_managment.service.MetricService;
import com.self_managment.service.QAService;

@Component("graficBean")
@Scope("session")
public class GraficWebController {
	public String metric1 = "QA_MONITORS";
	public String metric2 = "QA_PTS_ACHIEVED";
	public String metric3 = "QA_PTS_POSSIBLE";

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

	private String today = now();
	private String currentCampaign;
	private double sueldoFijo;
	private double sueldoVariableProyectado;
	private Integer metricaCalculadaProy;
	private List<CampaignMetric> metrics;
	private String agentName;

	ApplicationContext ctx = FacesContextUtils
			.getWebApplicationContext(FacesContext.getCurrentInstance());

	AgentService agentService = (AgentService) ctx.getBean("agentService");
	QAService qaService = (QAService) ctx.getBean("qaService");
	MetricService metricService = (MetricService) ctx.getBean("metricService");

	Agent currentAgent = agentService.findById(1);

	Double QAPossiblePoints;

	org.jfree.data.time.TimeSeries pop = new org.jfree.data.time.TimeSeries(
			"Linea de Crecimiento");

	Integer CantDias = 0;

	public void generaGrafico(OutputStream out, Object data) throws IOException {

		pop.add(new Day(1, 10, 2010), 0);
		pop.add(new Day(18, 10, 2010), sueldoALaFecha());
		pop.add(new Day(30, 10, 2010), sueldoProyectado());

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(pop);

		JFreeChart chart1 = ChartFactory.createTimeSeriesChart("Tendencia",
				"Dias", "Proyeccion Metrica", dataset, true, true, false);

		BufferedImage buffImg = chart1.createBufferedImage(500, // width
				375, // height
				BufferedImage.TYPE_INT_RGB, // image type
				null);

		ImageIO.write(buffImg, "jpeg", out);

	}

	private String now() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(Calendar.getInstance().getTime());
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

	public Integer getMetric1_optim() {
		return metric1_optim;
	}

	public double getSueldoProyectado() {
		sueldoVariableProyectado = sueldoProyectado();
		return sueldoVariableProyectado;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getToday() {
		return today;
	}

	public void setCurrentCampaign(String currentCampaign) {
		this.currentCampaign = currentCampaign;
	}

	public String getCurrentCampaign() {
		if (this.currentCampaign == null) {
			this.currentCampaign = currentAgent.getCampaign().getName();
		}
		return this.currentCampaign;
	}

	public void setSueldoFijo(double sueldoFijo) {
		this.sueldoFijo = sueldoFijo;
	}

	public double getSueldoFijo() {
		return currentAgent.getGrossSalary();
	}

	public void setSueldoVariableProyectado(double sueldoVariableProyectado) {
		this.sueldoVariableProyectado = sueldoVariableProyectado;
	}

	public double getSueldoVariableProyectado() {
		return sueldoVariableProyectado;
	}

	public void setMetrics(List<CampaignMetric> metrics) {
		this.metrics = metrics;
	}

	public List<CampaignMetric> getMetrics() {
		if (metrics == null) {
			metrics = currentAgent.getCampaign().getCampaignMetric();
		}
		return metrics;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentName() {
		return currentAgent.getName();
	}

	public Integer getMetricCalculateProy() {
//		metricaCalculadaProy = (30 - CantDias) * sumaTotal;
		return metricaCalculadaProy;
	}

}
