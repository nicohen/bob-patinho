package com.self_managment.persistance.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.Metric;
import com.self_managment.persistance.dao.MetricDao;

@Repository("metricDao")
public class MetricDaoImpl extends GenericDaoImpl<Metric, Serializable>
	implements MetricDao {

    @Override
    protected Class<Metric> getEntityClass() {
	return Metric.class;
    }

}
