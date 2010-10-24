package com.self_managment.service;

import java.io.Serializable;
import java.util.Date;

import com.self_managment.model.entity.QA;

public interface QAService extends CRUDService<QA, Serializable> {

    public Number sumPossiblePoints(Integer docket, Date dateFrom, Date dateTo);

    public Number sumQAMonitors(Integer docket, Date dateFrom, Date dateTo);

    public Number sumAchievedPoints(Integer docket, Date dateFrom, Date dateTo);
}
