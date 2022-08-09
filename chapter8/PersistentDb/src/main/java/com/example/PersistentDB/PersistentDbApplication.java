package com.example.PersistentDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class PersistentDbApplication {
    @Autowired
    private StockRepository stockRepository;


    @RequestMapping("/stocks")
    public List<Stock> stocks(){
        return stockRepository.findAllBy();
    }

    @RequestMapping("/stocks/create")
    public String createStock(){
        Stock stock = new Stock();
        stock.setStock_id(6);
        stock.setCompanyName("Coffee Planet");
        stock.setCeo("Ahmad");
        stock.setSymbol("COF");
        stock.setPrice(5.00d);
        stockRepository.save(stock);
        return "Data successfully created";
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

        SpringApplication.run(PersistentDbApplication.class, args);
    }

}
