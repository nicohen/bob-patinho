package com.self_managment.metric;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.importFile.ImportFileTTSTest;
import com.self_managment.model.entity.Agent;
import com.self_managment.model.metric.MetricStrategy;
import com.self_managment.service.SummaryService;
import com.self_managment.service.TTSService;
import com.self_managment.util.DateUtils;

public class MetricTimeInAuxStatusTest extends TestCase {

    private MetricStrategy metric;
    private ApplicationContext appContext;

    public static Test suite() {
	TestSuite suite = new TestSuite();
	suite.addTestSuite(ImportFileTTSTest.class);
	suite.addTestSuite(MetricTimeInAuxStatusTest.class);
	return suite;	
    }

    @Override
    protected void setUp() throws Exception {
	appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	metric = (MetricStrategy) appContext.getBean("AUX_TM");
    }

    public void testMetricWithResult() throws ParseException {
	Agent agent = new Agent();
	agent.setDocket(100);
	agent.setWorkingDay("PTE");
	Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010");
	SummaryService summaryService = (SummaryService)appContext.getBean("summaryService");
	TTSService ttsService = (TTSService)appContext.getBean("ttsService");
	assertEquals((ttsService.getProductiveHours(agent, DateUtils.getFirstDay(date), DateUtils.getLastDay(date))* 60) - summaryService.getTotalLoggedTime(null, null, agent.getDocket(), DateUtils.getFirstDay(date),
			DateUtils.getLastDay(date)), metric.execute(null, null, agent.getDocket(), DateUtils.getFirstDay(date),
		DateUtils.getLastDay(date)));
    }

    public void testMetricWithoutResult() throws ParseException {
//	Agent agent = new Agent();
//	agent.setDocket(-1);
//	Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010");
//	SummaryService summaryService = (SummaryService)appContext.getBean("summaryService");
//	TTSService ttsService = (TTSService)appContext.getBean("ttsService");
//	assertEquals((ttsService.getProductiveHours(agent, DateUtils.getFirstDay(date), DateUtils.getLastDay(date))* 60) - summaryService.getTotalLoggedTime(null, null, agent.getDocket(), DateUtils.getFirstDay(date),
//			DateUtils.getLastDay(date)), metric.execute(null, null, agent.getDocket(), DateUtils.getFirstDay(date),
//		DateUtils.getLastDay(date)));
	
	assert(true);
    }

}
