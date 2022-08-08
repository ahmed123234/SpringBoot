package com.oreilly.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class SpringBootJndiApplication extends SpringBootServletInitializer {

	 @Autowired
	    private JdbcTemplate template;

	 @Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			return builder.sources(SpringBootJndiApplication.class);
		}
	 
	 @RequestMapping("/stocks")
	  public List<Map<String, Object>> stocks(){
	      return template.queryForList("select * from stock");
	   }
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJndiApplication.class, args);
	}

}
