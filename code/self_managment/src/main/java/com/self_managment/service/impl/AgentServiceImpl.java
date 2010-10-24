package com.self_managment.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self_managment.model.entity.Agent;
import com.self_managment.persistance.dao.AgentDao;
import com.self_managment.service.AgentService;

@Service("agentService")
public class AgentServiceImpl implements AgentService {

    @Autowired
    AgentDao agentDao;

    public void setAgentDao(AgentDao agentDao) {
	this.agentDao = agentDao;
    }

    @Override
    public void delete(Agent persistentObject) {
	agentDao.delete(persistentObject);
    }

    @Override
    public List<Agent> findAll() {
	return agentDao.findAll();
    }

    @Override
    public List<Agent> findAllByProperty(String propertyName, Object value) {
	return agentDao.findAllByProperty(propertyName, value);
    }

    @Override
    public Agent findById(Serializable id) {
	return agentDao.findById(id);
    }

    @Override
    public Serializable save(Agent newInstance) {
	return agentDao.save(newInstance);
    }

    @Override
    public void saveOrUpdate(Agent transientObject) {
	agentDao.saveOrUpdate(transientObject);
    }

    @Override
    public void update(Agent transientObject) {
	agentDao.update(transientObject);
    }

}
