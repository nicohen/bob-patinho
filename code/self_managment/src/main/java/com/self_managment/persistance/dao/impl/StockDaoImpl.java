package com.self_managment.persistance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.self_managment.model.entity.Stock;
import com.self_managment.persistance.dao.StockDao;
import com.self_managment.util.CustomHibernateDaoSupport;

@Repository("stockDao")
public class StockDaoImpl extends CustomHibernateDaoSupport implements StockDao {

    public void save(Stock stock) {
	getHibernateTemplate().save(stock);
    }

    public void update(Stock stock) {
	getHibernateTemplate().update(stock);
    }

    public void delete(Stock stock) {
	getHibernateTemplate().delete(stock);
    }

    public Stock findByStockCode(String stockCode) {
	List list = getHibernateTemplate().find("from Stock where stockCode=?",
		stockCode);
	return (Stock) list.get(0);
    }

    @Override
    public List<Stock> findAll() {
	return getHibernateTemplate().find("from Stock");
    }

}