package com.self_managment.service;


import java.io.Serializable;

import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.TTS;

public interface TTSService extends CRUDService<TTS, Serializable> {

	public double getOvertimeSalary(Agent agent, int month, int year);
	public double getProductiveHours(Agent agent, int month, int year);
}
