package com.oreilly.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {
//	@RequestMapping("/viewResolver")
//	public String viewResolver() {
//		return "viewresolver";
//	}
//	
//	@ResponseBody
//	@RequestMapping("/cat")
//	public Cat cat() {
//		return new Cat("Athena", "Bengal", 1);
//	}

	@RequestMapping("/thymeleaf")
	public String tleaf() {
		return "template";
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
