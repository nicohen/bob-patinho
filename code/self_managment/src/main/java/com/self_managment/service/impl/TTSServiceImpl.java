package com.self_managment.service.impl;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.self_managment.model.entity.Agent;
import com.self_managment.model.entity.TTS;

import com.self_managment.persistance.dao.TTSDao;
import com.self_managment.service.TTSService;
import com.self_managment.util.DateUtils;


@Service("ttsService")
public class TTSServiceImpl implements TTSService {

	@Autowired
	TTSDao ttsDao;

	@Override
	public void delete(TTS persistentObject) {
		ttsDao.delete(persistentObject);
	}

	@Override
	public List<TTS> findAll() {
		return ttsDao.findAll();
	}

	@Override
	public List<TTS> findAllByProperty(String propertyName, Object value) {
		return ttsDao.findAllByProperty(propertyName, value);
	}

	@Override
	public TTS findById(Serializable id) {
		return ttsDao.findById(id);
	}

	@Override
	public Serializable save(TTS newInstance) {
		return ttsDao.save(newInstance);
	}

	@Override
	public void saveOrUpdate(TTS transientObject) {
		ttsDao.saveOrUpdate(transientObject);
	}

	@Override
	public void update(TTS transientObject) {
		ttsDao.update(transientObject);
	}
	
	public long getExtraHours50Percent(Agent agent, int month, int year)
	{
		int workDayHours = agent.getWorkDayHours();
		List<TTS> dates = ttsDao.findByAgentDateFromDateTo(agent.getDocket(), DateUtils.getFirstDay(month, year), DateUtils.getLastDay(month, year));
		return getExtraHours50Percent(dates, workDayHours);
	}
	
	@SuppressWarnings("deprecation")
	private long getExtraHours50Percent(List<TTS> dates, int workDayHours)
	{
		long extraHours50Percent = 0;
		for(TTS date : dates)
		{
			Date dateIn = date.getDate();
			String[] aux = date.getScheduleEntered().split(":");
			dateIn.setHours(Integer.parseInt(aux[0]));
			dateIn.setMinutes(Integer.parseInt(aux[1]));
			aux = date.getScheduleGoneOut().split(":");
			Date dateOut = date.getBulgingDate();
			dateOut.setHours(Integer.parseInt(aux[0]));
			dateOut.setMinutes(Integer.parseInt(aux[1]));
			Calendar calendarIn = Calendar.getInstance();
			calendarIn.setTime(dateIn);
			Calendar calendarOut = Calendar.getInstance();
			calendarOut.setTime(dateOut);
			//Si no es fin de semana...
			if ((calendarOut.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)&&((calendarOut.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY))&&(calendarIn.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)&&((calendarIn.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)))
			{
				long offset = dateOut.getTime() - dateIn.getTime();
				int workedHours = (int)((offset / 1000) / 60) / 60;
				if(workedHours>workDayHours)
				{
					extraHours50Percent += workedHours - workDayHours;
				}
			}
		}
		return extraHours50Percent;
	}
	
	public long getExtraHours100Percent(Agent agent, int month, int year)
	{
		List<TTS> dates = ttsDao.findByAgentDateFromDateTo(agent.getDocket(), DateUtils.getFirstDay(month, year), DateUtils.getLastDay(month, year));
		return getExtraHours100Percent(dates);
	}
	
	@SuppressWarnings("deprecation")
	private long getExtraHours100Percent(List<TTS> dates)
	{
		long extraHours100Percent = 0;
		for(TTS date : dates)
		{
			Date dateIn = date.getDate();
			String[] aux = date.getScheduleEntered().split(":");
			dateIn.setHours(Integer.parseInt(aux[0]));
			dateIn.setMinutes(Integer.parseInt(aux[1]));
			aux = date.getScheduleGoneOut().split(":");
			Date dateOut = date.getBulgingDate();
			dateOut.setHours(Integer.parseInt(aux[0]));
			dateOut.setMinutes(Integer.parseInt(aux[1]));
			Calendar calendarIn = Calendar.getInstance();
			calendarIn.setTime(dateIn);
			Calendar calendarOut = Calendar.getInstance();
			calendarOut.setTime(dateOut);
			if ((calendarOut.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)||((calendarOut.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))||(calendarIn.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)||((calendarIn.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)))
			{
				long offset = dateOut.getTime() - dateIn.getTime();
				int workedHours = (int)((offset / 1000) / 60) / 60;
				extraHours100Percent += workedHours;
			}
		}
		return extraHours100Percent;
	}	

	public double getOvertimeSalary(Agent agent, int month, int year)
	{	
		
		double overtimeSalary = getExtraHours50Percent(agent, month, year) * getOvertimeValue50(agent);
		overtimeSalary += getExtraHours100Percent(agent, month, year) * getOvertimeValue100(agent);		
		return overtimeSalary;
	}
	
	public double getOvertimeValue50(Agent agent)
	{
		return (agent.getGrossSalary()/(22*agent.getWorkDayHours()))*1.5;
	}
	
	public double getOvertimeValue100(Agent agent)
	{
		return (agent.getGrossSalary()/(22*agent.getWorkDayHours()))*2;
	}
	
	public long getProductiveHours(Agent agent, int month, int year)
	{
		long productiveHours = 22 * agent.getWorkDayHours() + getExtraHours50Percent(agent, month, year) + getExtraHours100Percent(agent, month, year);
		return productiveHours;
	}
	
	public long getProductiveHours(Agent agent, Date dateFrom, Date dateTo)
	{
		List<TTS> dates = ttsDao.findByAgentDateFromDateTo(agent.getDocket(), dateFrom, dateTo);
		int days =  DateUtils.getWorkingDaysCount(dateFrom, dateTo);
		long productiveHours = days * agent.getWorkDayHours() + getExtraHours50Percent(dates, agent.getWorkDayHours()) + getExtraHours100Percent(dates);
		return productiveHours;
	}

}
