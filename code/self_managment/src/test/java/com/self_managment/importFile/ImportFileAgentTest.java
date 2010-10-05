package com.self_managment.importFile;

import java.io.IOException;
import java.util.Date;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.model.entity.Campaign;
import com.self_managment.model.entity.Supervisor;
import com.self_managment.model.entity.Campaign.CampaignType;
import com.self_managment.service.AgentService;
import com.self_managment.service.CampaignService;
import com.self_managment.service.SupervisorService;

public class ImportFileAgentTest extends TestCase {

	private ImportFile importFile;
	private CampaignService campaignService;
	private SupervisorService supervisorService;
	private AgentService agentService;

	@Override
	protected void setUp() throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring/config/beanlocations.xml");

		importFile = (ImportFile) appContext.getBean("importFileAgent");
		campaignService = (CampaignService) appContext
				.getBean("campaignService");
		supervisorService = (SupervisorService) appContext
				.getBean("supervisorService");
		
		agentService =  (AgentService) appContext
		.getBean("agentService");
	}

	public void testImportFile() throws IOException {

		// Save supervisor
		Supervisor supervisor121 = new Supervisor();
		supervisor121.setId(121);
		supervisor121.setDni((long) 33223332);
		supervisor121.setName("Pedro");
		supervisor121.setSurname("Picapiedras");
		//supervisorService.save(supervisor121);
		supervisorService.saveOrUpdate(supervisor121);
		Supervisor supervisor131 = new Supervisor();
		supervisor131.setId(131);
		supervisor131.setDni((long) 32332223);
		supervisor131.setName("Pepe");
		supervisor131.setSurname("Biondi");
		//supervisorService.save(supervisor131);
		supervisorService.saveOrUpdate(supervisor131);

		// Save campaign
		Campaign campaign34 = new Campaign();
		campaign34.setId(34);
		campaign34.setName("campaign34");
		campaign34.setType(CampaignType.INBOUND);
		campaign34.setOptimValue(0d);
		campaign34.setObjetiveValue(0d);
		campaign34.setMinimumValue(0d);
		campaign34.setUnsatisfactoryValue(0d);
		campaign34.setStartDate(new Date());
		campaignService.saveOrUpdate(campaign34);
	//	campaignService.save(campaign34);

		importFile.importFile();
		
		assertEquals(agentService.findById(34).getDni(), new Long(30345235l));
		
	}
}
