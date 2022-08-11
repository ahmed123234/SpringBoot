package com.oreilly.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootToolsApplication {

	@RequestMapping("/")
	public String hello() {
		return "Hello World, I am using Java language to write my code....";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootToolsApplication.class, args);
	}

}
