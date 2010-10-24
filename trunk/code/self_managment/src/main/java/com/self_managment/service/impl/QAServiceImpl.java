package com.self_managment.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self_managment.model.entity.QA;
import com.self_managment.persistance.dao.QADao;
import com.self_managment.service.QAService;

@Service("qaService")
public class QAServiceImpl implements QAService {

    @Autowired
    QADao qaDao;

    @Override
    public void delete(QA persistentObject) {
	qaDao.delete(persistentObject);
    }

    @Override
    public List<QA> findAll() {
	return qaDao.findAll();
    }

    @Override
    public List<QA> findAllByProperty(String propertyName, Object value) {
	return qaDao.findAllByProperty(propertyName, value);
    }

    @Override
    public QA findById(Serializable id) {
	return qaDao.findById(id);
    }

    @Override
    public Serializable save(QA newInstance) {
	return qaDao.save(newInstance);
    }

    @Override
    public void saveOrUpdate(QA transientObject) {
	qaDao.saveOrUpdate(transientObject);
    }

    @Override
    public void update(QA transientObject) {
	qaDao.update(transientObject);
    }

    @Override
    public Number sumPossiblePoints(Integer docket, Date dateFrom, Date dateTo) {
	return qaDao.sumPossiblePoints(docket, dateFrom, dateTo);
    }

    @Override
    public Number sumQAMonitors(Integer docket, Date dateFrom, Date dateTo) {
	return qaDao.sumQAMonitors(docket, dateFrom, dateTo);
    }

    @Override
    public Number sumAchievedPoints(Integer docket, Date dateFrom, Date dateTo) {
	return qaDao.sumAchievedPoints(docket, dateFrom, dateTo);
    }

}
