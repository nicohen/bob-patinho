package com.self_managment.importFile;

import java.io.IOException;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.service.STSService;


public class ImportFileSTSTest extends TestCase {

    private ImportFile importFile;
    private STSService stsService;

    @Override
    protected void setUp() throws Exception {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	importFile = (ImportFile) appContext.getBean("importFileSTS");
	stsService = (STSService) appContext.getBean("stsService");
    }

    public void testImportFile() throws IOException {

	// Save agent
	// agents are load from hf test

	importFile.importFile("src/main/resources/test/files/STS_Septiembre2010.csv");

	assertEquals(stsService.findAllByProperty("pk.agent.docket", 100).get(0)
		.getAgent().getDocket(), new Integer(100));

    }
}
