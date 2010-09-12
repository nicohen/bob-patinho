package com.self_managment.web.controller;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;

import com.self_managment.model.entity.Stock;
import com.self_managment.service.StockService;
import com.self_managment.web.util.JSFUtil;

@Component("stockBean")
@Scope("session")
public class StockWebController {
    private List<Stock> stocks;
    private Stock stock = new Stock();
    private StockService service;

    public StockWebController() {
	ApplicationContext ctx = FacesContextUtils
		.getWebApplicationContext(FacesContext.getCurrentInstance());

	service = (StockService) ctx.getBean("stockService");
    }

    public List<Stock> getStocks() {
	if (stocks == null)
	    stocks = service.findAll();
	return stocks;
    }

    public Stock getStock() {
	return stock;
    }

    public String update() {
	try {
	    if (stock.getStockId() == null)
		service.save(stock);
	    else
		service.update(stock);
	    setStock(new Stock());
	} catch (Exception e) {
	    JSFUtil.addErrorMessage("Error");
	} finally {
	    stocks = service.findAll();
	}
	return "";
    }

    public void setStock(Stock stock) {
	this.stock = stock;
    }

    public String create() {
	setStock(new Stock());
	return "";
    }

    public String delete() {
	try {
	    service.delete(stock);
	} catch (Exception e) {
	    JSFUtil.addErrorMessage("Error");
	} finally {
	    stocks = service.findAll();
	}
	return "";
    }
}
