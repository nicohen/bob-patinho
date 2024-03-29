package com.self_managment.web.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.ResourceBundle;

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

import com.self_managment.model.entity.CampaignMetric;
import com.self_managment.util.DateUtils;
import com.self_managment.web.util.JSFUtil;

public class MetricChart {
    private CampaignMetric campaignMetric;
    private Date period;
    private Integer campaignId;
    private Integer supervisorId;
    private Integer docket;

    public MetricChart(Integer campaignId, Integer supervisorId, Integer docket, CampaignMetric campaignMetric, Date period) {
	super();
	this.campaignMetric = campaignMetric;
	this.period = period;
	this.campaignId = campaignId;
	this.supervisorId = supervisorId;
	this.docket = docket;
    }

    public BufferedImage createBufferedImage(int width, int height) {

	JFreeChart chart = ChartFactory.createTimeSeriesChart(campaignMetric
		.getMetric().getCode().toUpperCase(), JSFUtil.getMsg("label.days"),
		JSFUtil.getMsg("label.metricProjection"), getDataSet(), true, true, false);
	
	chart.setBorderPaint(new Color(0,0,0));
	chart.getTitle().setPaint(new Color(0,0,0));
	chart.getXYPlot().setBackgroundPaint(new Color(255,255,255));
	chart.getXYPlot().setRangeGridlinePaint(new Color(255,255,255));
	chart.getXYPlot().setDomainGridlinePaint(new Color(0,0,0));

	ResourceBundle rb = ResourceBundle.getBundle("messages");
	
	chart.getXYPlot().addRangeMarker(
		getMarker(campaignMetric.getOptim(), Color.GREEN, rb
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
	chart.getXYPlot().getRenderer().setSeriesPaint(0, new Color(0,0,0));
	chart.getXYPlot().getRenderer().setSeriesShape(0, new Rectangle(-1,-1,2,2));
	chart.getXYPlot().getRenderer().setSeriesPaint(1, new Color(200,200,200));
	chart.getXYPlot().getRenderer().setSeriesShape(1, new Rectangle(-1,-1,2,2));
	
	return chart.createBufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB, null);

    }

    private TimeSeriesCollection getDataSet() {

	Date dateTo = DateUtils.isOldPeriod(period) ? DateUtils
		.getLastDay(period) : new Date();
	Date date = DateUtils.getFirstDay(period);
	Date firstDay = DateUtils.getFirstDay(period);
	
	if (period.after(dateTo)) // future
	    return new TimeSeriesCollection();

	TimeSeries pop = new org.jfree.data.time.TimeSeries(JSFUtil.getMsg("label.acumulatedPoints"));
	
	int dayCount = DateUtils.getDay(dateTo) - DateUtils.getDay(date) + 1;

	Integer[] xData = new Integer[dayCount];
	Number[] yData = new Number[dayCount];
	while (date.before(dateTo)) {

	    int day = DateUtils.getDay(date);
	    xData[day - 1] = day;
	    yData[day - 1] = campaignMetric.getMetric().execute(campaignId, supervisorId, docket, firstDay, date)
		    .doubleValue();
	    
	    pop.add(new Day(date), yData[day - 1]);

	    date = org.apache.commons.lang.time.DateUtils.addDays(date, 1);
	}

	TimeSeriesCollection dataset = new TimeSeriesCollection();
	dataset.addSeries(pop);

	if (!DateUtils.isOldPeriod(period)) {
	    TimeSeries popLeastSquares = new TimeSeries(JSFUtil.getMsg("label.projection"));

	    double[] linearFit = Statistics.getLinearFit(xData, yData);

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
