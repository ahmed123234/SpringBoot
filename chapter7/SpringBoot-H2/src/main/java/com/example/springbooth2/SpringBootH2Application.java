package com.example.springbooth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringBootH2Application {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/stocks")
    public List<Map<String, Object>> stocks(){
        return jdbcTemplate.queryForList("select * from stock");
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootH2Application.class, args);
    }

}
