package com.self_managment.service;

import com.self_managment.model.entity.Stock;

public interface StockService {

    void save(Stock stock);

    void update(Stock stock);

    void delete(Stock stock);

    Stock findByStockCode(String stockCode);

}
