package com.self_managment.importFile;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.service.AgentService;
import com.self_managment.service.SummaryService;

public class ImportFileSummaryTest extends TestCase {

    private ImportFile importFile;
    private SummaryService summaryService;
    private AgentService agentService;

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
	TestSuite suite = new TestSuite();
	suite.addTestSuite(ImportFileAgentTest.class);
	suite.addTestSuite(ImportFileSummaryTest.class);
	return suite;
    }

    @Override
    protected void setUp() throws Exception {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	importFile = (ImportFile) appContext.getBean("importFileSummary");
	summaryService = (SummaryService) appContext.getBean("summaryService");
	agentService = (AgentService) appContext.getBean("agentService");
    }

    public void testImportFile() throws IOException {

	importFile.importFile();

	/*
	 * assertEquals(qaService.findAllByProperty("achievedPointsQuantity",
	 * 27) .get(0).getPosiblePointsQuantity(), new Integer(30));
	 */

    }
}
