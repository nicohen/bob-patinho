package com.self_managment.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Long getAmountOfTimeInAnAvailableCallStatus(Integer campaignId,
	    Integer supervisorId, Integer docket, Date dateFrom, Date dateTo) {
	return summaryDao.getAmountOfTimeInAnAvailableCallStatus(campaignId,
		supervisorId, docket, dateFrom, dateTo);
    }

    @Override
    public Double getAverageTalkTime(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	return summaryDao.getAverageTalkTime(campaignId, supervisorId, docket,
		dateFrom, dateTo);
    }

    @Override
    public Long getNCH(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	return summaryDao.getNCH(campaignId, supervisorId, docket, dateFrom,
		dateTo);
    }

    @Override
    public Long getTransferPCT(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	return summaryDao.getTransferPCT(campaignId, supervisorId, docket,
		dateFrom, dateTo);
    }

    @Override
    public Long getTotalLoggedTime(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	return summaryDao.getTotalLoggedTime(campaignId, supervisorId, docket,
		dateFrom, dateTo);
    }

}
