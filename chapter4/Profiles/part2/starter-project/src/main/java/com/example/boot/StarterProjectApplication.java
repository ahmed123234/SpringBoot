package com.example.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StarterProjectApplication {

	@Autowired
	private MyMessage myMessage;
	@RequestMapping("/")
	public String welcome() {
		return "Welcome, your lucky number is " + myMessage.getMessageValue();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StarterProjectApplication.class, args);
	}

}
