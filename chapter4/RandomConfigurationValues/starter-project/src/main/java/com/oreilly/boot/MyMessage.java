package com.oreilly.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class MyMessage {
	@Value("${my.message}")
	private String messageValue;
		
	@Value("${my.key}")
	private String key;
				
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getMessageValue() {
		return messageValue;
	}
	
	public void setMessageValue(String messageValue) {
		this.messageValue = messageValue;
	}
		
}
