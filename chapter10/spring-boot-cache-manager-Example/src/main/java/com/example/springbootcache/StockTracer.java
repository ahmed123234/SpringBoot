package com.example.springbootcache;

import net.sf.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.ehcache.CacheManager;
import javax.cache.annotation.CacheResult;
import java.util.Random;

@Component
public class StockTracer {
    @Autowired
    private CacheManager cacheManager;

    public double getPriceWithManager(String symbol){
        Cache cache = cacheManager.getCache("priceCache");
        //return Double.parseDouble(cache.get(symbol).getObjectValue().toString());
        // return (Double) cache.get(symbol).getObjectValue();
        return Double.valueOf(cache.get(symbol).getObjectValue().toString());
    }
    @CacheResult(cacheName = "price")
    public double getPrice(String symbol){
        System.out.println("Generating the price...");
        return 20 + (200 - 20) * new Random().nextDouble(); // Generate a number between 20 & 200
    }

    @CacheResult(cacheName = "priceCache")
    public double getPriceBySymbol (String symbol){
        System.out.println("Generating the price with getPriceBySymbol method");
        return 30 + (200 - 30) * new Random().nextDouble();
    }
}
