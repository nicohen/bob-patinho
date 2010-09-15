package com.self_managment.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self_managment.model.entity.Campaign;
import com.self_managment.persistance.dao.impl.GenericDaoImpl;
import com.self_managment.service.CRUDService;

@Service("campaignService")
public class CampaignServiceImpl implements CRUDService<Campaign, Serializable> {

    @Autowired
    GenericDaoImpl<Campaign, Serializable> campaingDao;

    public void setCampaingDao(
	    GenericDaoImpl<Campaign, Serializable> campaingDao) {
	this.campaingDao = campaingDao;
    }

    @Override
    public void delete(Campaign persistentObject) {
	campaingDao.delete(persistentObject);
    }

    @Override
    public List<Campaign> findAll() {
	return campaingDao.findAll();
    }

    @Override
    public List<Campaign> findAllByProperty(String propertyName, Object value) {
	return campaingDao.findAllByProperty(propertyName, value);
    }

    @Override
    public Campaign findById(Serializable id) {
	return campaingDao.findById(id);
    }

    @Override
    public Serializable save(Campaign newInstance) {
	return campaingDao.save(newInstance);
    }

    @Override
    public void saveOrUpdate(Campaign transientObject) {
	campaingDao.saveOrUpdate(transientObject);
    }

    @Override
    public void update(Campaign transientObject) {
	campaingDao.update(transientObject);
    }

}
