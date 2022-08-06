package com.oreilly.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class SpringBoot2Application {


	@RequestMapping("/thymeleaf")
	public String tleaf() {
		return "template";
	}
		
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2Application.class, args);
	}

}
