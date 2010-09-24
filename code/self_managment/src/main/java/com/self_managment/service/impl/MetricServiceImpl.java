package com.self_managment.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self_managment.model.entity.Metric;
import com.self_managment.persistance.dao.MetricDao;
import com.self_managment.service.MetricService;

@Service("metricService")
public class MetricServiceImpl implements MetricService {

    @Autowired
    MetricDao metricDao;

    public void setMetricDao(MetricDao metricDao) {
	this.metricDao = metricDao;
    }

    @Override
    public void delete(Metric persistentObject) {
	metricDao.delete(persistentObject);
    }

    @Override
    public List<Metric> findAll() {
	return metricDao.findAll();
    }

    @Override
    public List<Metric> findAllByProperty(String propertyName, Object value) {
	return metricDao.findAllByProperty(propertyName, value);
    }

    @Override
    public Metric findById(Serializable id) {
	return metricDao.findById(id);
    }

    @Override
    public Serializable save(Metric newInstance) {
	return metricDao.save(newInstance);
    }

    @Override
    public void saveOrUpdate(Metric transientObject) {
	metricDao.saveOrUpdate(transientObject);
    }

    @Override
    public void update(Metric transientObject) {
	metricDao.update(transientObject);
    }

}
