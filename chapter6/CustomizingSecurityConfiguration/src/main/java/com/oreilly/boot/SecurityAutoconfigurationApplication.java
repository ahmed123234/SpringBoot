package com.oreilly.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableWebSecurity
public class SecurityAutoconfigurationApplication extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin();
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityAutoconfigurationApplication.class, args);
	}

}
