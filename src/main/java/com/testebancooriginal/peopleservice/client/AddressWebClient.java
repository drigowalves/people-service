package com.testebancooriginal.peopleservice.client;

import static java.lang.String.*;

import com.testebancooriginal.peopleservice.client.response.AddressResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@AllArgsConstructor
public class AddressWebClient {

    private final WebClient addressRestWebClient;

    public AddressResponse getAddressByZipCode(String zipCode) {
        return addressRestWebClient
            .get()
            .uri(format("%s/json/", zipCode))
            .retrieve()
            .bodyToMono(AddressResponse.class)
            .block();
    }

}
