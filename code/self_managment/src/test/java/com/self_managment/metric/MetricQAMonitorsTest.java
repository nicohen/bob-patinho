package com.self_managment.metric;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.metric.MetricStrategy;

public class MetricQAMonitorsTest extends TestCase {

    private MetricStrategy metric;

    public static Test suite() {
	return new TestSuite(MetricQAMonitorsTest.class);
    }

    @Override
    protected void setUp() throws Exception {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	metric = (MetricStrategy) appContext.getBean("QA_MONITORS");
    }

    public void testMetricWithResult() {
	Agent agent = new Agent();
	agent.setDocket(1);
	assertEquals(29L, metric.execute(agent));
    }

    public void testMetricWithoutResult() {
	Agent agent = new Agent();
	agent.setDocket(999);
	assertEquals(0L, metric.execute(agent));
    }
}
