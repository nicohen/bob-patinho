package com.self_managment.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self_managment.model.entity.Supervisor;
import com.self_managment.persistance.dao.SupervisorDao;
import com.self_managment.service.SupervisorService;

@Service("supervisorService")
public class SupervisorServiceImpl implements SupervisorService {

	@Autowired
	SupervisorDao supervisorDao;

	public void setMetricDao(SupervisorDao supervisorDao) {
		this.supervisorDao = supervisorDao;
	}

	@Override
	public void delete(Supervisor persistentObject) {
		supervisorDao.delete(persistentObject);
	}

	@Override
	public List<Supervisor> findAll() {
		return supervisorDao.findAll();
	}

	@Override
	public List<Supervisor> findAllByProperty(String propertyName, Object value) {
		return supervisorDao.findAllByProperty(propertyName, value);
	}

	@Override
	public Supervisor findById(Serializable id) {
		return supervisorDao.findById(id);
	}

	@Override
	public Serializable save(Supervisor newInstance) {
		return supervisorDao.save(newInstance);
	}

	@Override
	public void saveOrUpdate(Supervisor transientObject) {
		supervisorDao.saveOrUpdate(transientObject);
	}

	@Override
	public void update(Supervisor transientObject) {
		supervisorDao.update(transientObject);
	}

}
