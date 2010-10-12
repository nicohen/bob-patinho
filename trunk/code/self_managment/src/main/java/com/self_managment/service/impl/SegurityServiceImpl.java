package com.self_managment.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self_managment.model.entity.Segurity;

import com.self_managment.persistance.dao.SegurityDao;

import com.self_managment.service.SegurityService;

@Service("SegurityService")
public class SegurityServiceImpl implements SegurityService {

    @Autowired
    SegurityDao segurityDao;

    public void setMetricDao(SegurityDao segurityDao) {
	this.segurityDao = segurityDao;
    }

    @Override
    public void delete(Segurity persistentObject) {
	segurityDao.delete(persistentObject);
    }

    @Override
    public List<Segurity> findAll() {
	return segurityDao.findAll();
    }

    @Override
    public List<Segurity> findAllByProperty(String propertyName, Object value) {
	return segurityDao.findAllByProperty(propertyName, value);
    }

    @Override
    public Segurity findById(Serializable id) {
	return segurityDao.findById(id);
    }

    @Override
    public Serializable save(Segurity newInstance) {
	return segurityDao.save(newInstance);
    }

    @Override
    public void saveOrUpdate(Segurity transientObject) {
	segurityDao.saveOrUpdate(transientObject);
    }

    @Override
    public void update(Segurity transientObject) {
	segurityDao.update(transientObject);
    }

}