package com.testebancooriginal.peopleservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AddressWebClientConfig {

    @Value("${services.viacep.url-base}")
    private String viaCepUrlBase;

    @Bean
    public WebClient addressRestWebClient() {
        return WebClient.builder().baseUrl(viaCepUrlBase).build();
    }

}
