package com.example.PersistentDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    public Stock findBySymbol(String symbol);
    public Stock findStockByCompanyName(String name);
    public List<Stock> findAllByPrice(double price);
    public List<Stock> findAllBy ();
}