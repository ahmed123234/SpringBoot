package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableAutoConfiguration
@RestController
//@Import(MyConfiguration.class)
// OR using @ComponentScan
//@ComponentScan()
// @SpringBootApplication can be added instead of @EnableAutoConfiguration & @ComponentScan() 
//& @Configuration.
@SpringBootApplication
public class Application {
	
	@Autowired
	String message;
	
	@Value("${name}")
	String name;
	
	@Value("${tool}")
	String tool;

	@RequestMapping("/")
	public String home() {
		return "Hello " + name + ". You are working with " + tool + ". " + message;
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
