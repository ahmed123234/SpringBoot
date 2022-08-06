package com.example.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class Application {
	
	@Value("${name}")
	String name;
	
	@Value("${tool}")
	String tool;

	@RequestMapping("/")
	public String home() {
		return "Hello " + name + ". You are working with " + tool;
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
