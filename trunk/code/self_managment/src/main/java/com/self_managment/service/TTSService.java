package com.self_managment.service;


import java.io.Serializable;
import java.util.Date;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.TTS;

public interface TTSService extends CRUDService<TTS, Serializable> {

	public double getOvertimeSalary(Agent agent, int month, int year);
	public long getProductiveHours(Agent agent, int month, int year);
	public long getProductiveHours(Agent agent, Date dateFrom, Date dateTo);
}
