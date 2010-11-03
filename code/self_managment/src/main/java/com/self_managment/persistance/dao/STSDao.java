package com.self_managment.persistance.dao;

import java.io.Serializable;
import java.util.List;

import com.self_managment.model.entity.STS;


public interface STSDao extends GenericDao<STS, Serializable> {
	
	public List<STS> findByAgentMonthYear(Integer docket, Integer month, Integer year);

}
