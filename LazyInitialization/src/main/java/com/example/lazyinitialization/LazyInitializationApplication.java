package com.example.lazyinitialization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LazyInitializationApplication {

    @Bean("writer1")
    public Writer getWriter1() {
        return new Writer("Writer 1");
    }

    @Bean("writer2")
    public Writer getWriter2() {
        return new Writer("Writer 2");
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LazyInitializationApplication.class);

        application.setLazyInitialization(true);
        ApplicationContext context = application.run(args);
       // ApplicationContext context = SpringApplication.run(LazyInitializationApplication.class, args);

        Writer writer1 = context.getBean("writer1", Writer.class);
        writer1.write("First message");

        Writer writer2 = context.getBean("writer2", Writer.class);
        writer2.write("Second message");


    }

}
