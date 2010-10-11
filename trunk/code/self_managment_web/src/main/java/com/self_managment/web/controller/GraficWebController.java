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

	public void generaGrafico(OutputStream out, Object data) throws IOException {
		
		
		
		
         
		 XYSeries series = new XYSeries("titulo de la serie");
	        //como su nombre lo indica el primer valor sera asignado al eje X
	        //y el segundo al eje Y
	        series.add(1, 23);
	        series.add(2, 34);
	        series.add(3, 51);
	        series.add(4, 67);
	        series.add(5, 89);
	        series.add(6, 121);
	        series.add(7, 137);
	        //se crea un objeto XYDataset requerido mas adelante por el metodo que grafica
	      XYDataset juegoDatos= new XYSeriesCollection(series);
        
        

		 JFreeChart  chart1 = ChartFactory.createXYLineChart("Visitas", "Mes",
                 "visitas", juegoDatos, PlotOrientation.VERTICAL, true,
                 true, true);
		 
		    BufferedImage buffImg = chart1.createBufferedImage(
		                                500, //width
		                                375, //height
		                                BufferedImage.TYPE_INT_RGB, //image type
		                                null);
		  
		    
		    
		    ImageIO.write(buffImg, "jpeg", out);
		    
		}
	


	
	 
	
	
}
