package com.self_managment.persistance.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.self_managment.model.entity.TTS;

public interface TTSDao extends GenericDao<TTS, Serializable> {
	
	public List<TTS> findByAgentDateFromDateTo(Integer docket, Date dateFrom, Date dateTo);

}

