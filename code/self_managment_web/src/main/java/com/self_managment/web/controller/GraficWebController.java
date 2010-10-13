package com.self_managment.web.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
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

@Component("graficBean")
@Scope("session")

public class GraficWebController {
	public String metric1="QA_MONITORS";
	public String metric2="QA_PTS_ACHIEVED";
	public String metric3="QA_PTS_POSSIBLE";
    
	public Integer metric1_optim=80;
	public Integer metric1_objetivo=50;
	public Integer metric1_minimo=20;
	public Integer metric1_noSatisfactorio=10;
	public Integer metric2_optim=50;
	public Integer metric2_objetivo=35;
	public Integer metric2_minimo=15;
	public Integer metric2_noSatisfactorio=5;
	public Integer metric3_optim=35;
	public Integer metric3_objetivo=20;
	public Integer metric3_minimo=10;
	public Integer metric3_noSatisfactorio=5;
    
	public double value_metrics_optim=20.93;
	public double value_metrics_objetivo=15.65;
	public double value_metrics_minimo=10.95;
	public double value_metrics_noSatisfactorio=0.00;
    
	public Integer metric1_values=89;
	public Integer metric2_values=47;
	public Integer metric3_values=32;
    
	public Integer cantHoras=108;
	
	public double sueldoProyectado;
	public double sueldoReal;

	public void generaGrafico(OutputStream out, Object data) throws IOException {
		
		
		org.jfree.data.time.TimeSeries pop = new org.jfree.data.time.TimeSeries("Linea de Crecimiento", Day.class);
		pop.add(new Day(1, 10, 2010), 0);
		pop.add(new Day(18,10, 2010), sueldoALaFecha());
		pop.add(new Day(30, 10, 2010), sueldoProyectado());
	
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(pop);
		
		JFreeChart chart1 = ChartFactory.createTimeSeriesChart(
				"Tendencia", "Dias",
                "Proyeccion Sueldo",
		dataset,
		true,
		true,
		false);
		
		
         
		/* XYSeries series = new XYSeries("Sueldo Proyectado");
	        //como su nombre lo indica el primer valor sera asignado al eje X
	        //y el segundo al eje Y
	        series.add(1, 0);
	        series.add(18, sueldoALaFecha());
	        
	        series.add(30, sueldoProyectado());
	        series.add(4, 67);
	        series.add(5, 89);
	        series.add(6, 121);
	        series.add(7, 137);
	        series.add(8, 34);
	        series.add(9, 51);
	        series.add(10, 67);
	        series.add(11, 89);
	        series.add(12, 121);
	        series.add(13, 137);
	        series.add(14, 34);
	        series.add(15, 51);
	        series.add(16, 67);
	        series.add(17, 89);
	        series.add(18, 121);
	        series.add(19, 137);
	        series.add(20, 34);
	        series.add(21, 51);
	        series.add(22, 67);
	        series.add(23, 89);
	        series.add(24, 121);
	        series.add(25, 137);
	        series.add(26, 137);
	        series.add(27, 137);
	        series.add(28, 137);
	        series.add(29, 137);
	        series.add(30, 137);*/
	        //se crea un objeto XYDataset requerido mas adelante por el metodo que grafica
	    /*  XYDataset juegoDatos= new XYSeriesCollection(series);
        
        

		 JFreeChart  chart1 = ChartFactory.createXYLineChart("Tendencia", "Dias",
                 "Proyeccion Sueldo", juegoDatos, PlotOrientation.VERTICAL, true,
                 true, true);*/
		 
		    BufferedImage buffImg = chart1.createBufferedImage(
		                                500, //width
		                                375, //height
		                                BufferedImage.TYPE_INT_RGB, //image type
		                                null);
		  
		    
		    
		    ImageIO.write(buffImg, "jpeg", out);
		    
		}
	

	public double sueldoALaFecha(){
		if (metric1_values>metric1_optim && metric1_values>metric2_optim && metric1_values>metric3_optim)
			 sueldoReal=cantHoras*value_metrics_optim;
		 return sueldoReal;
	}
	
	 public double sueldoProyectado(){
		 double proyectado_metric1;
		 double proyectado_metric2;
		 double proyectado_metric3;
		 proyectado_metric1=(160*metric1_values)/cantHoras;
		 proyectado_metric2=(160*metric2_values)/cantHoras;
		 proyectado_metric3=(160*metric3_values)/cantHoras;
		 
		 
		 if (proyectado_metric1>metric1_optim && proyectado_metric2>metric2_optim && proyectado_metric3>metric3_optim)
			 sueldoProyectado=160*value_metrics_optim;
		 return sueldoProyectado;
	 }
	 
	 public Integer getMetric1_optim(){
		 return metric1_optim;
	 }
	 
	 public double getSueldoProyectado(){
		 sueldoProyectado=sueldoProyectado();
		 return sueldoProyectado;
	 }
	
	
}
