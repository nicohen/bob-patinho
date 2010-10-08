package com.self_managment.persistance.dao;

import java.io.Serializable;
import java.util.List;

import com.self_managment.model.entity.Supervisor;

public interface SupervisorDao extends GenericDao<Supervisor, Serializable> {
	public List<Supervisor> findAllSupervisorsWithoutCampaign();
}
