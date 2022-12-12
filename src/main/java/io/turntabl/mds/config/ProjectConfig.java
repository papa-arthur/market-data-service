package io.turntabl.mds.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.mds.service.StompDataRetrieval;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class ProjectConfig {
    public static String EXCHANGE1_URL = "https://exchange.matraining.com";
    public static String EXCHANGE2_URL = "https://exchange2.matraining.com";

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.create();
    }

    @Bean
    public StompDataRetrieval stompDataRetrieval(){
        return new StompDataRetrieval();
    }






}
