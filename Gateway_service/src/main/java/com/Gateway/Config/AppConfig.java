package com.Gateway.Config;


import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }


    @Bean
    public HttpMessageConverters customConverters() {
        // You can add custom converters here if needed
        return new HttpMessageConverters();
    }
}
