package com.self_managment.persistance.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


import com.self_managment.model.entity.Segurity;
import com.self_managment.persistance.dao.SegurityDao;

@Repository("segurityDao")
public class SegurityDaoImpl extends GenericDaoImpl<Segurity, Serializable>
	implements SegurityDao  {

    @Override
    protected Class<Segurity> getEntityClass() {
	return Segurity.class;
    }

}
