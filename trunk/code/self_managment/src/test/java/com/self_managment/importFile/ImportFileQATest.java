package com.self_managment.importFile;

import java.io.IOException;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.service.AgentService;
import com.self_managment.service.QAService;

public class ImportFileQATest extends TestCase {

	private ImportFile importFile;
	private QAService qaService;
	private AgentService agentService;

	@Override
	protected void setUp() throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring/config/beanlocations.xml");

		importFile = (ImportFile) appContext.getBean("importFileQA");
		qaService = (QAService) appContext.getBean("qaService");
		agentService = (AgentService) appContext.getBean("agentService");
	}

	public void testImportFile() throws IOException {

		// Save agent
		// agents are load from hf test

		importFile.importFile();

		assertEquals(qaService.findAllByProperty("achievedPointsQuantity", 27)
				.get(0).getPosiblePointsQuantity(), new Integer(30));

	}
}
