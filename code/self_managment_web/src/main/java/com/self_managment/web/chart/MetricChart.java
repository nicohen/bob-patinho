package com.self_managment.web.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.statistics.Statistics;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.util.DateUtils;

public class MetricChart {
    private CampaignMetric campaignMetric;
    private Date period;
    private Agent agent;

    public MetricChart(CampaignMetric campaignMetric, Date period, Agent agent) {
	super();
	this.campaignMetric = campaignMetric;
	this.period = period;
	this.agent = agent;
    }

    public BufferedImage createBufferedImage(int width, int height) {

	JFreeChart chart = ChartFactory.createTimeSeriesChart(campaignMetric
		.getMetric().getCode().toUpperCase(), "Dias",
		"Proyeccion Metrica", getDataSet(), true, true, false);

	ResourceBundle rb = ResourceBundle.getBundle("messages");

	chart.getXYPlot().addRangeMarker(
		getMarker(campaignMetric.getOptim(),
			ChartColor.VERY_DARK_GREEN, rb
				.getString("label.metric.optim")));
	chart.getXYPlot().addRangeMarker(
		getMarker(campaignMetric.getObjective(), Color.YELLOW, rb
			.getString("label.metric.objective")));
	chart.getXYPlot().addRangeMarker(
		getMarker(campaignMetric.getMinimum(), Color.ORANGE, rb
			.getString("label.metric.minimum")));
	chart.getXYPlot().addRangeMarker(
		getMarker(campaignMetric.getUnsatisfactory(), Color.RED, rb
			.getString("label.metric.unsatisfactory")));

	return chart.createBufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB, null);

    }
    private Date now() {
    	return Calendar.getInstance().getTime();
        }


    private TimeSeriesCollection getDataSet() {
	TimeSeries pop = new TimeSeries("Puntos Acumulados");
	TimeSeries popLeastSquares = new TimeSeries("Proyeccion");
	SortedSet<Day> acumDays = new TreeSet<Day>();
	
	Date dateTo = DateUtils.getLastDay(period);
	Date date = DateUtils.getFirstDay(period);
	Date leastSquaresFirstDate = (Date) date.clone();
	
	System.out.println("Este es el dia " + Calendar.getInstance().getTime());
	int dayTo=0;
	
	double acum = 0;
	if(period.getMonth() <  now().getMonth() )
	{
		
		while (date.before(dateTo)) {
		    acum += campaignMetric.getMetric().execute(agent, date, date)
			    .doubleValue();
		    pop.add(new Day(date), acum);
		    acumDays.add(new Day(date));
		    date = org.apache.commons.lang.time.DateUtils.addDays(date, 1);
		}
		
	}
	else{
		
		while (date.before(Calendar.getInstance().getTime())) {
			
			dayTo=dayTo+1;
		    acum += campaignMetric.getMetric().execute(agent, date, date)
			    .doubleValue();
		    pop.add(new Day(date), acum);
		    acumDays.add(new Day(date));
		    date = org.apache.commons.lang.time.DateUtils.addDays(date, 1);
	
			
		
		}
		 acum=30*acum/dayTo;
		 pop.add(new Day(dateTo), acum);
		  acumDays.add(new Day(dateTo));
		
	

		
	}
	
	double[] linearFit = getLinearFit(acumDays,pop);
	double startY = getY(linearFit[0],linearFit[1],date);
	double endY = getY(linearFit[0],linearFit[1],dateTo);
	popLeastSquares.addOrUpdate(new Day(leastSquaresFirstDate),startY);
	popLeastSquares.addOrUpdate(new Day(dateTo),endY);
	
	TimeSeriesCollection dataset = new TimeSeriesCollection();
	dataset.addSeries(pop);
	dataset.addSeries(popLeastSquares);

	return dataset;
    }

    private double getY(double b, double a, Date date) {
    	// Y = aX + b
    	Day d = new Day(date);
    	return (d.getDayOfMonth()*a)+b;
	}

	private double[] getLinearFit(SortedSet<Day> acumDays, TimeSeries pop) {
    	Iterator<Day> it = acumDays.iterator();
    	Double[] xValues = new Double[acumDays.size()];
    	Double[] yValues = new Double[acumDays.size()];
    	int i=0;
    	
    	while(it.hasNext()) {
    		Day d = it.next();
    		xValues[i] = new Double(d.getDayOfMonth()); 
    		yValues[i++] = (Double) pop.getValue(d);
    	}
    	
    	return Statistics.getLinearFit(xValues, yValues);
	}

	private Marker getMarker(double value, Color color, String label) {
	final Marker marker = new ValueMarker(value);
	marker.setPaint(color);
	marker.setLabel(label);
	marker.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
	marker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
	marker.setStroke(new BasicStroke(3.f));

	return marker;
    }

    
    public static void main(String[] args) {
        //         Create a simple XY chart
    	XYSeries series = new XYSeries("XYGraph");
        series.add(1, 1);
        series.add(1, 2);
        series.add(2, 1);
        series.add(3, 9);
        series.add(4, 10);
    	XYSeries series2 = new XYSeries("XYGraph2");
        series2.add(1, 1);
        series2.add(4, 2);
        series2.add(5, 1);
        series2.add(6, 9);
        series2.add(7, 10);
        //         Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(series2);
        //         Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart("XY Chart", // Title
                "x-axis", // x-axis Label
                "y-axis", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
            );
        
        try {
            ChartUtilities.saveChartAsJPEG(new File("C:chart.jpg"), chart, 500,
                300);
        } catch (IOException e) {
            System.err.println("Problem occurred creating chart.");
        }
    }

}
