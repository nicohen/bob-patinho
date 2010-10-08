package com.self_managment.service;

import java.io.Serializable;
import java.util.List;
import com.self_managment.model.entity.Supervisor;

public interface SupervisorService extends CRUDService<Supervisor, Serializable> {
	public List<Supervisor> findAllSupervisorsWithoutCampaign();
}
