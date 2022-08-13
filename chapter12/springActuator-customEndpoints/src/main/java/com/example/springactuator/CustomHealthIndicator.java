package com.example.springactuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
//        return  Health.status("ok").build();

        return Health.down().withDetail("Error message", "There is no connection to the service.").build();
    }
}
