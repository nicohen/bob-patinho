package com.self_managment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.model.entity.Stock;
import com.self_managment.service.StockService;

/**
 * Hello world!
 * 
 */
public class App {
    public static void main(String[] args) {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");

	StockService stockService = (StockService) appContext
		.getBean("stockService");

	/** insert * */
	Stock stock = new Stock();
	stock.setStockCode("7668");
	stock.setStockName("HAIO");
	stockService.save(stock);

	/** select * */
	Stock stock2 = stockService.findByStockCode("7668");
	System.out.println(stock2);

	/** update * */
	stock2.setStockName("HAIO-1");
	stockService.update(stock2);

	/** delete * */
	stockService.delete(stock2);
	System.out.println("Done");

    }
}
