package com.oreilly.boot;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;
@Component
public class CustomSecurityEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

	@Override
	public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
		System.out.println(event.getException().getMessage());
		
	}
	// the first thing we have to do is to provide a generic type for the applicationListener <E>
	// E specified the event we would to listen for.
}
