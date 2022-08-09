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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringBootMongoApplication {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/stocks/insert")
    private Stock stocks(){
        Stock stock =new Stock();
        stock.setStock_id(1);
        stock.setCeo("Mark");
        stock.setCompanyName("Facebook");
        stock.setSymbol("FB");
        stock.setPrice(118.25d);
        mongoTemplate.insert(stock,"stock");
        return mongoTemplate.findOne(new BasicQuery("{symbol: 'FB'}"), Stock.class);

    }

    @RequestMapping("/stocks/add")
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
        mongoTemplate.insert(stock,"stock");

        return " Successfully created!";

    }

    @RequestMapping("/stocks/getAll")
    public List<Stock> getAllStocks(){
        return mongoTemplate.findAll(Stock.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoApplication.class, args);
    }

}
