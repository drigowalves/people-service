package com.testebancooriginal.peopleservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AddressWebClientConfig {

    @Bean
    public WebClient addressRestWebClient() {
        return WebClient.builder().baseUrl("viacep.com.br/ws/").build();
    }

}
