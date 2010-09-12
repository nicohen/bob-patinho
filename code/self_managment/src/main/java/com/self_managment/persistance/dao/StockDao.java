package com.self_managment.persistance.dao;

import java.util.List;

import com.self_managment.model.entity.Stock;

public interface StockDao {
    void save(Stock stock);

    void update(Stock stock);

    void delete(Stock stock);

    Stock findByStockCode(String stockCode);

    List<Stock> findAll();

}
