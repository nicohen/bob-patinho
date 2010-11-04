package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    
    @SuppressWarnings("unchecked")
    public List<TTS> findByAgentDateFromDateTo(Integer docket, Date dateFrom, Date dateTo) {

	return getHibernateTemplate().find(
		"from TTS where pk.agent.docket=? and bulgingDate > ? and pk.date <= ?",
		new Object[] { docket, dateFrom,dateTo });
    }

}