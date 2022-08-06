package com.oreilly.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class StarterProjectApplication {
	@Autowired
	private MyMessage myMessage;
	@RequestMapping("/")
	public String welcome() {
		return "Welcome, your lucky number is " + myMessage.getMessageValue();
	}
	
	@GetMapping("/key")
	public String key() {
		return "Welcome, your lucky key is " + myMessage.getKey();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StarterProjectApplication.class, args);
	}

}
