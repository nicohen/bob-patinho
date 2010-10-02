package com.self_managment.persistance;

import java.io.Serializable;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.model.entity.Campaign;
import com.self_managment.model.entity.Campaign.CampaignType;
import com.self_managment.service.CRUDService;

public class CampaignCRUDTest extends TestCase {

	private CRUDService<Campaign, Serializable> service;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public CampaignCRUDTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CampaignCRUDTest.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring/config/beanlocations.xml");

		service = (CRUDService<Campaign, Serializable>) appContext
				.getBean("campaignService");
	}

	public void testAdd() {
		Campaign campaign = new Campaign();
		campaign.setId(1);
		campaign.setName("testAdd");
		campaign.setType(CampaignType.INBOUND);
		campaign.setOptimValue(0d);
		campaign.setObjetiveValue(0d);
		campaign.setMinimumValue(0d);
		campaign.setUnsatisfactoryValue(0d);
		campaign.setStartDate(new Date());

		service.save(campaign);

		assertEquals(campaign.getId(), service.findAllByProperty("id", 1)
				.get(0).getId());

		service.delete(campaign);
	}

	public void testGet() {
		Campaign campaign = new Campaign();
		campaign.setId(2);
		campaign.setName("testGet");
		campaign.setType(CampaignType.INBOUND);
		campaign.setOptimValue(0d);
		campaign.setObjetiveValue(0d);
		campaign.setMinimumValue(0d);
		campaign.setUnsatisfactoryValue(0d);
		campaign.setStartDate(new Date());

		Integer id = (Integer) service.save(campaign);

		assertEquals(campaign.getId(), service.findById(id).getId());

		service.delete(campaign);

		assertNull(service.findById(2));
	}

}
