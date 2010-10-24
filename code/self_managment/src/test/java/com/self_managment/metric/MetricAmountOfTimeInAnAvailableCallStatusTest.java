package com.self_managment.metric;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.metric.MetricStrategy;
import com.self_managment.util.DateUtils;

public class MetricAmountOfTimeInAnAvailableCallStatusTest extends TestCase {

    private MetricStrategy metric;

    public static Test suite() {
	return new TestSuite(
		MetricAmountOfTimeInAnAvailableCallStatusTest.class);
    }

    @Override
    protected void setUp() throws Exception {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	metric = (MetricStrategy) appContext.getBean("AVAIL_TM");
    }

    public void testMetricWithResult() throws ParseException {
	Agent agent = new Agent();
	agent.setDocket(1);
	Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010");
	assertEquals(562L, metric.execute(agent, DateUtils.getFirstDay(date),
		DateUtils.getLastDay(date)));
    }

    public void testMetricWithoutResult() throws ParseException {
	Agent agent = new Agent();
	agent.setDocket(999);
	Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010");
	assertEquals(0L, metric.execute(agent, DateUtils.getFirstDay(date),
		DateUtils.getLastDay(date)));
    }

    public void testMetricWithNullAgent() {
	assertNull(metric.execute(null, null, null));
    }

}
