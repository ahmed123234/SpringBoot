package com.oreilly.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyMessage {
	
	@Value("${my.messageValue}")
	private int messageValue;
	
	@Value("${my.key}")
	private String key;
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getMessageValue() {
		return messageValue;
	}

	public void setMessageValue(int messageValue) {
		this.messageValue = messageValue;
	}
	
	

}
