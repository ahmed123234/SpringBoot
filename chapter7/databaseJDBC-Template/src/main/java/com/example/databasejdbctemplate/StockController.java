package com.example.databasejdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StockController {
    @Autowired
    private JdbcTemplate template;


    @RequestMapping("/stocks")
    public List<Map<String, Object>> stocks(){
        return template.queryForList("select * from stock");
    }
}
