package com.self_managment.persistance.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.Agent;
import com.self_managment.persistance.dao.AgentDao;

@Repository("agentDao")
public class AgentDaoImpl extends GenericDaoImpl<Agent, Serializable>
	implements AgentDao {

    @Override
    protected Class<Agent> getEntityClass() {
	return Agent.class;
    }

}
