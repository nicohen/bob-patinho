package com.self_managment.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.Summary;
import com.self_managment.persistance.dao.SummaryDao;
import com.self_managment.service.SummaryService;

@Service("summaryService")
public class SummaryServiceImpl implements SummaryService {

    @Autowired
    SummaryDao summaryDao;

    @Override
    public void delete(Summary persistentObject) {
	summaryDao.delete(persistentObject);
    }

    @Override
    public List<Summary> findAll() {
	return summaryDao.findAll();
    }

    @Override
    public List<Summary> findAllByProperty(String propertyName, Object value) {
	return summaryDao.findAllByProperty(propertyName, value);
    }

    @Override
    public Summary findById(Serializable id) {
	return summaryDao.findById(id);
    }

    @Override
    public Serializable save(Summary newInstance) {
	return summaryDao.save(newInstance);
    }

    @Override
    public void saveOrUpdate(Summary transientObject) {
	summaryDao.saveOrUpdate(transientObject);
    }

    @Override
    public void update(Summary transientObject) {
	summaryDao.update(transientObject);
    }

    @Override
    public Long getAmountOfTimeInAnAvailableCallStatus(Agent agent,
	    Date dateFrom, Date dateTo) {
	if (agent == null || dateFrom == null || dateTo == null)
	    return null;
	return summaryDao.getAmountOfTimeInAnAvailableCallStatus(agent,
		dateFrom, dateTo);
    }

    @Override
    public Double getAverageTalkTime(Agent agent, Date dateFrom, Date dateTo) {
	if (agent == null || dateFrom == null || dateTo == null)
	    return null;
	
	return summaryDao.getAverageTalkTime(agent, dateFrom, dateTo);
    }

    @Override
    public Long getTotalLoggedTime(Agent agent, Date dateFrom, Date dateTo) {
	if (agent == null || dateFrom == null || dateTo == null)
	    return null;
	
	return summaryDao.getTotalLoggedTime(agent, dateFrom, dateTo);
    }

}
