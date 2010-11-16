package com.self_managment.persistance.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.STS;
import com.self_managment.persistance.dao.STSDao;
import com.self_managment.util.DateUtils;

@Repository("stsDao")
public class STSDaoImpl extends GenericDaoImpl<STS, Serializable> implements
	STSDao {

    @Override
    protected Class<STS> getEntityClass() {
	return STS.class;
    }

    @SuppressWarnings("unchecked")
    public List<STS> findByAgentMonthYear(Integer docket, Integer month,
	    Integer year) {

	return getHibernateTemplate().find(
		"from STS where pk.agent.docket=? and bulgingDate > ? and pk.date <= ?",
		new Object[] { docket, DateUtils.getFirstDay(month, year),
			DateUtils.getLastDay(month, year) });
    }

	@Override
	public Double getScheduledAdherence(final Integer campaignId, final Integer supervisorId,
			final Integer docket, final Date dateFrom, final Date dateTo) {
		
		Object o = getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	
            	String sql = "	SELECT 	SUM(" +
        				"					TIME_TO_SEC(" +
            			"						ADDTIME(" +
            			"							IF(SUBTIME(t.schedule_entered, s.schedule_entered) > '00:00:00', " +
            			"								SUBTIME(t.schedule_entered, s.schedule_entered), '00:00:00'), " +
            			"							IF(SUBTIME(s.schedule_gone_out, t.schedule_gone_out) > '00:00:00', " +
            			" 								SUBTIME(s.schedule_gone_out, t.schedule_gone_out), '00:00:00')" +
            			"						)" +
            			"					)" +
            			"				)" +
            			"		FROM   sts s " +
            			" 			INNER JOIN tts t ON t.docket = s.docket AND t.date = s.date " +
            			"			INNER JOIN agent a ON a.docket = s.docket " +
            			"			INNER JOIN supervisor sup ON sup.supervisor_id = a.supervisor_id AND sup.campaign_id = a.campaign_id " +
            			"		WHERE s.date BETWEEN ? and ?";
            	
            	Integer id;
            	if (campaignId != null) {
            		sql += " AND sup.campaign_id = ?";
            		id = campaignId;
            	} else if (supervisorId != null) {
            		sql += " AND sup.supervisor_id = ?";
            		id = supervisorId;
            	} else {
            		sql += " AND s.docket = ?";
            		id = docket;
            	}
            			            	
                Query query = session.createSQLQuery(sql);
                
                query.setDate(0, dateFrom);
                query.setDate(1, dateTo);
                query.setInteger(2, id);
                return query.uniqueResult();
            }
		});
		
		return o == null ? 0.0 : new Double(o.toString()) / 60;
	}

}