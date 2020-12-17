package com.testebancooriginal.peopleservice.service;

import com.testebancooriginal.peopleservice.client.AddressWebClient;
import com.testebancooriginal.peopleservice.entity.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressWebClient addressWebClient;

    public Address getAddressByZipCode(String zipCode) {
        return addressWebClient.getAddressByZipCode(zipCode).toAddressDomain();
    }

}
