package com.example.springbootmongo;

import com.mongodb.DBObject;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.MongoDbFactoryParser;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringBootMongoApplication {

    @Autowired
    private StockRepository stockRepository;

    @RequestMapping("/stocks")
    public List<Stock> getStocks(){
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
    @RequestMapping("/stocks/ceos/{ceo}")
    public Stock getAllStocks(@PathVariable("ceo") String ceo){

        return stockRepository.findByCeo(ceo);
    }


    @RequestMapping("/stocks/insert")
    private String addStock(@RequestParam("id") int id,
                           @RequestParam("ceo") String ceo,
                           @RequestParam("company") String companyName,
                           @RequestParam("symbol") String symbol,
                           @RequestParam("price") double price){
        Stock stock =new Stock();
        stock.setStock_id(id);
        stock.setCeo(ceo);
        stock.setCompanyName(companyName);
        stock.setSymbol(symbol);
        stock.setPrice(price);
        stockRepository.insert(stock);

        return " Successfully created!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoApplication.class, args);
    }

}
