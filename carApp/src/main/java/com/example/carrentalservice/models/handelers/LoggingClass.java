package com.example.carrentalservice.models.handelers;

import com.example.carrentalservice.services.user.AppUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
public class LoggingClass {
    private Logger logger = LoggerFactory.getLogger(LoggingClass.class);

    @Bean
    public void logInfoMessage(String message)  {
        logger.info(message);
    }

    @Bean
    public void logErrorMessage(String message)  {
        logger.error(message);
    }

}
