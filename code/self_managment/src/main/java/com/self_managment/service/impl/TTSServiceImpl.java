package com.self_managment.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.self_managment.model.entity.TTS;

import com.self_managment.persistance.dao.TTSDao;
import com.self_managment.service.TTSService;


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

	

}
