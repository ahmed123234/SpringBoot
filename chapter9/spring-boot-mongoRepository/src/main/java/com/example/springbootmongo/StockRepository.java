package com.example.springbootmongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StockRepository extends MongoRepository<Stock, Integer> {

    public Stock findBySymbol(String symbol);
    public Stock findStockByCompanyName(String name);
    public List<Stock> findAllByPrice(double price);
    public List<Stock> findAllBy ();
    public Stock findByCeo(String ceo);

}
