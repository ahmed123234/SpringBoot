package com.example.springbootdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringBootDataApplication {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private  EntityManager entityManager;

    @RequestMapping("/stocks")
    public List<Stock> stocks(){
        return entityManager.createQuery("select s from Stock s").getResultList();
    }

    @RequestMapping("/stocks/symbols/{symbol}")
    public Stock stocks(@PathVariable("symbol") String symbol){
        return stockRepository.findBySymbol(symbol);
    }

    @RequestMapping("stocks/companies/{company-name}")
    public Stock stockName(@PathVariable("company-name") String companyName) {
        return stockRepository.findStockByCompanyName(companyName);
    }

    @RequestMapping("stocks/prices/{price}")
    public List<Stock> stockName(@PathVariable("price") double price) {
        return stockRepository.findAllByPrice(price);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataApplication.class, args);
    }

}
