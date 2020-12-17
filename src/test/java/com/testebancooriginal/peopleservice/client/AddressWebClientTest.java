package com.testebancooriginal.peopleservice.client;

import static org.assertj.core.api.Assertions.assertThat;

import com.testebancooriginal.peopleservice.client.response.AddressResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressWebClientTest {

    @Autowired
    private AddressWebClient addressWebClient;

    @Test
    public void shouldGetAddressByZipCode() {
        AddressResponse addressResponse = addressWebClient.getAddressByZipCode("06036048");

        assertThat(addressResponse.getStreet()).isNotBlank();
        assertThat(addressResponse.getComplement()).isNotNull();
        assertThat(addressResponse.getNeighborhood()).isNotBlank();
        assertThat(addressResponse.getCity()).isNotBlank();
        assertThat(addressResponse.getState()).isNotBlank();
        assertThat(addressResponse.getZipCode()).isNotBlank();
    }

}
