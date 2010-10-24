package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.TTS;
import com.self_managment.persistance.dao.TTSDao;
import com.self_managment.util.DateUtils;

@Repository("ttsDao")
public class TTSDaoImpl extends GenericDaoImpl<TTS, Serializable> implements
	TTSDao {

    @Override
    protected Class<TTS> getEntityClass() {
	return TTS.class;
    }

    @SuppressWarnings("unchecked")
    public List<TTS> findByAgentMonthYear(Integer docket, Integer month,
	    Integer year) {

	return getHibernateTemplate().find(
		"from TTS where pk.agent.docket=? and bulgingDate >= ? and pk.date <= ?",
		new Object[] { docket, DateUtils.getFirstDay(month, year),
			DateUtils.getLastDay(month, year) });
    }

}