package io.turntabl.mds.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfig {
    public static String EXVHANGE1_URL = "https://exchange.matraining.com";
    public static String EXVHANGE2_URL = "https://exchange2.matraining.com";

//    @Bean
//    public WebClient webClient(){
//        return WebClient.builder().build();
//    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.create();
    }






}
