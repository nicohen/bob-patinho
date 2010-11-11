package com.self_managment.importFile;

import java.io.IOException;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.service.AgentService;

public class ImportFileAgentTest extends TestCase {

    private ImportFile importFile;
    private AgentService agentService;

    @Override
    protected void setUp() throws Exception {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	importFile = (ImportFile) appContext.getBean("importFileAgent");
	agentService = (AgentService) appContext.getBean("agentService");
    }

    public void testImportFile() throws IOException {

	importFile.importFile("src/main/resources/test/files/HF System.csv");

	assertEquals(agentService.findById(100).getDni(), new Long(30345235L));

    }
}
