package com.self_managment.importFile;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.service.TTSService;

public class ImportFileTTSTest extends TestCase {

    private ImportFile importFile;
    private TTSService ttsService;
    
    public static Test suite() {
    	TestSuite suite = new TestSuite();
    	suite.addTestSuite(ImportFileAgentTest.class);
    	suite.addTestSuite(ImportFileTTSTest.class);
    	return suite;
    }

    @Override
    protected void setUp() throws Exception {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	importFile = (ImportFile) appContext.getBean("importFileTTS");
	ttsService = (TTSService) appContext.getBean("ttsService");
    }

    public void testImportFile() throws IOException {

	// Save agent
	// agents are load from hf test

	importFile.importFile();

	assertEquals(ttsService.findAllByProperty("pk.agent.docket", 100).get(0)
		.getAgent().getDocket(), new Integer(100));

    }
}
