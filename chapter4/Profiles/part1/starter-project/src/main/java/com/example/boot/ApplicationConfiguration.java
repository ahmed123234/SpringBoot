package com.example.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfiguration {

	@Bean
	@Profile("prod")
	public MyMessage myMessage(@Value("${my.messageValue}") String messageValue) {
		MyMessage myMessage = new MyMessage();
		myMessage.setMessageValue(messageValue);
		return myMessage;
	}
	
	@Bean
	@Profile("dev")
	public MyMessage myMessagedev() {
		MyMessage myMessage = new MyMessage();
		myMessage.setMessageValue("This is the dev method");
		return myMessage;
	}
}
