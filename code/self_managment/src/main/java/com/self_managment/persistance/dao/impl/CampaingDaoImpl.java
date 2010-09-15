package com.self_managment.persistance.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.Campaign;

@Repository("campaignDao")
public class CampaingDaoImpl extends GenericDaoImpl<Campaign, Serializable> {

    @Override
    protected Class<Campaign> getEntityClass() {
	return Campaign.class;
    }

}
