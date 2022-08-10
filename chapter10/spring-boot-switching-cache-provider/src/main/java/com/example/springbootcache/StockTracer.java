package com.example.springbootcache;

import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheResult;
import java.util.Random;

@Component
public class StockTracer {
    @CacheResult(cacheName = "price")
    public double getPrice(String symbol){
        System.out.println("Generating the price...");
        return 20 + (200 - 20) * new Random().nextDouble(); // Generate a number between 20 & 200
    }
}
