package com.ibm.motoInsure.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.sendgrid.SendGrid;

@Configuration
@PropertySource("classpath:application.properties")
public class SendGridConfig {

    @Value("${sendgrid.api.key}") 
    private String sendApiKey;
    @Bean
    public SendGrid getSendGridkey() {
        return new SendGrid(sendApiKey);
    }
}
