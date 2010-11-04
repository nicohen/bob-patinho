package com.self_managment.web.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.ResourceBundle;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.statistics.Statistics;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.util.DateUtils;

public class MetricChart {
    private CampaignMetric campaignMetric;
    private Date period;
    private Agent agent;
    private double endY;

    public double getEndY() {
	return endY;
    }

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
	
	chart.getXYPlot().setRenderer(new XYSplineRenderer());
	
	return chart.createBufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB, null);

    }

    private TimeSeriesCollection getDataSet() {

	Date dateTo = DateUtils.isOldPeriod(period) ? DateUtils
		.getLastDay(period) : new Date();
	Date date = DateUtils.getFirstDay(period);
	
	if (period.after(dateTo)) // future
	    return new TimeSeriesCollection();

	TimeSeries pop = new org.jfree.data.time.TimeSeries("Puntos Acumulados");
	
	double acum = 0;

	int dayCount = DateUtils.getDay(dateTo) - DateUtils.getDay(date) + 1;

	Integer[] xData = new Integer[dayCount];
	Number[] yData = new Number[dayCount];
	while (date.before(dateTo)) {

	    int day = DateUtils.getDay(date);
	    xData[day - 1] = day;
	    acum += campaignMetric.getMetric().execute(agent, date, date)
		    .doubleValue();
	    yData[day - 1] = acum;

	    pop.add(new Day(date), acum);

	    date = org.apache.commons.lang.time.DateUtils.addDays(date, 1);
	}

	TimeSeriesCollection dataset = new TimeSeriesCollection();
	dataset.addSeries(pop);

	if (!DateUtils.isOldPeriod(period)) {
	    TimeSeries popLeastSquares = new TimeSeries("Proyeccion");

	    double[] linearFit = Statistics.getLinearFit(xData, yData);

	    Date firstDay = DateUtils.getFirstDay(period);
	    Date lastDay = DateUtils.getLastDay(period);

	    // Y = aX + b
	    double startY = linearFit[1] * DateUtils.getDay(firstDay)
		    + linearFit[0];
	    double endY = linearFit[1] * DateUtils.getDay(lastDay)
		    + linearFit[0];

	    popLeastSquares.add(new Day(firstDay), startY);
	    popLeastSquares.add(new Day(lastDay), endY);

	    dataset.addSeries(popLeastSquares);
	}

	return dataset;

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

}
