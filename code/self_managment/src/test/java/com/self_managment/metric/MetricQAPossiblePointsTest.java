package com.self_managment.metric;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.metric.MetricStrategy;

public class MetricQAPossiblePointsTest extends TestCase {

    private MetricStrategy metric;

    public static Test suite() {
	return new TestSuite(MetricQAPossiblePointsTest.class);
    }

    @Override
    protected void setUp() throws Exception {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	metric = (MetricStrategy) appContext.getBean("QA_PTS_POSSIBLE");
    }

    public void testMetricWithResult() {
	Agent agent = new Agent();
	agent.setDocket(1);
	assertEquals(262L, metric.execute(agent));
    }

    public void testMetricWithoutResult() {
	Agent agent = new Agent();
	agent.setDocket(999);
	assertEquals(0L, metric.execute(agent));
    }
}
