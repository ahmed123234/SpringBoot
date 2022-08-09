package com.example.springbootjpaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class SpringBootJpaExampleApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaExampleApplication.class, args);
    }

}
