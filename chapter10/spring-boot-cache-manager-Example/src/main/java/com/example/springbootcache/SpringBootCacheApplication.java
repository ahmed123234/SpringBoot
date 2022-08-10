package com.example.springbootcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootCacheApplication implements CommandLineRunner {

    @Autowired
    private StockTracer stockTracer;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("THe price is ...");

        System.out.println(stockTracer.getPriceBySymbol("F"));
        for (int x = 0; x<10; x++){
            System.out.println(stockTracer.getPrice("F"));
        }
        System.out.println(stockTracer.getPrice("FB"));

        System.out.println("Getting price with new method ");
        System.out.println(stockTracer.getPriceWithManager("F"));
    }
}
