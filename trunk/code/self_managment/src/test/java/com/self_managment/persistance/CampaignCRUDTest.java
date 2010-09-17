package com.self_managment.persistance;

import java.io.Serializable;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.model.entity.Campaign;
import com.self_managment.service.CRUDService;

public class CampaignCRUDTest extends TestCase {

    private CRUDService<Campaign, Serializable> service;

    /**
     * Create the test case
     * 
     * @param testName
     *                name of the test case
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
	campaign.setCode("12345");
	campaign.setName("12345");

	service.save(campaign);

	assertEquals(campaign.getCode(), service.findAllByProperty("code",
		"12345").get(0).getCode());

	service.delete(campaign);
    }

    public void testGet() {
	Campaign campaign = new Campaign();
	campaign.setCode("12345");
	campaign.setName("12345");

	Integer id = (Integer) service.save(campaign);

	assertEquals(campaign.getCode(), service.findById(id).getCode());

	service.delete(campaign);

	assertNull(service.findById(1));
    }

}
