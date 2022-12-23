package com.here;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class HereWebApplication extends WebMvcConfigurerAdapter {


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer config) {
        config.favorPathExtension(false);
    }

    public static void main(String[] args) {
        SpringApplication.run(HereWebApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
