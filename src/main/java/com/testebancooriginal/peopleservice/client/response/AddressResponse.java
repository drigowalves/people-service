package com.testebancooriginal.peopleservice.client.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.testebancooriginal.peopleservice.entity.Address;
import lombok.Data;

@Data
public class AddressResponse {

    @JsonProperty("logradouro")
    private String street;
    @JsonProperty("complemento")
    private String complement;
    @JsonProperty("bairro")
    private String neighborhood;
    @JsonProperty("localidade")
    private String city;
    @JsonProperty("cep")
    private String zipCode;
    @JsonProperty("uf")
    private String state;

    public Address toAddressDomain() {
        return Address.builder()
            .street(street)
            .complement(complement)
            .neighborhood(neighborhood)
            .city(city)
            .zipCode(zipCode)
            .state(state)
            .build();
    }

}
