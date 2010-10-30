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
import org.jfree.data.time.Day;
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

    public MetricChart(CampaignMetric campaignMetric, Date period, Agent agent) {
	super();
	this.campaignMetric = campaignMetric;
	this.period = period;
	this.agent = agent;
    }

    public BufferedImage createBufferedImage(int width, int height) {

	JFreeChart chart = ChartFactory.createTimeSeriesChart(campaignMetric
		.getMetric().getCode().toUpperCase(), "Días",
		"Proyección Métrica", getDataSet(), true, true, false);

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

    private TimeSeriesCollection getDataSet() {
	org.jfree.data.time.TimeSeries pop = new org.jfree.data.time.TimeSeries(
		"Puntos Acumulados");

	Date dateTo = DateUtils.getLastDay(period);
	Date date = DateUtils.getFirstDay(period);

	double acum = 0;
	while (date.before(dateTo)) {
	    acum += campaignMetric.getMetric().execute(agent, date, date)
		    .doubleValue();
	    pop.add(new Day(date), acum);
	    date = org.apache.commons.lang.time.DateUtils.addDays(date, 1);
	}

	TimeSeriesCollection dataset = new TimeSeriesCollection();
	dataset.addSeries(pop);

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
