package com.self_managment.persistance.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


import com.self_managment.model.entity.TTS;
import com.self_managment.persistance.dao.TTSDao;

@Repository("ttsDao")
public class TTSDaoImpl extends GenericDaoImpl<TTS, Serializable> implements
		TTSDao {

	@Override
	protected Class<TTS> getEntityClass() {
		return TTS.class;
	}
	


}