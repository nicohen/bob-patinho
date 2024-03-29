package com.self_managment.metric;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.model.metric.MetricStrategy;
import com.self_managment.service.SummaryService;
import com.self_managment.util.DateUtils;

public class MetricAverageTalkTimeTest extends TestCase {

    private MetricStrategy metric;
    private SummaryService summaryService;

    public static Test suite() {
	return new TestSuite(MetricAverageTalkTimeTest.class);
    }

    @Override
    protected void setUp() throws Exception {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	metric = (MetricStrategy) appContext.getBean("AVG_TALK_TM");
	summaryService = (SummaryService) appContext.getBean("summaryService");
    }

    public void testMetricWithResult() throws ParseException {
	Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010");
	Date dateFrom = DateUtils.getFirstDay(date);
	Date dateTo = DateUtils.getLastDay(date); 
	assertEquals(summaryService.getAverageTalkTime(null, null, 100, dateFrom, dateTo), metric.execute(null, null, 100, dateFrom,
		dateTo));
    }

    public void testMetricWithoutResult() throws ParseException {
	Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010");
	assertEquals(0D, metric.execute(null, null, -1, DateUtils.getFirstDay(date),
		DateUtils.getLastDay(date)));
    }
}
