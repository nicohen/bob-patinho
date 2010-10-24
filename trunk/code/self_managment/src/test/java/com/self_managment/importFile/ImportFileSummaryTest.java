package com.self_managment.importFile;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.service.SummaryService;

public class ImportFileSummaryTest extends TestCase {

    private ImportFile importFile;
    private SummaryService summaryService;

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
    }

    public void testImportFile() throws IOException {

	importFile.importFile();

	assertEquals(summaryService.findAllByProperty("inCall",
		200).get(0).getInCall(), new Integer(200));

    }
}
