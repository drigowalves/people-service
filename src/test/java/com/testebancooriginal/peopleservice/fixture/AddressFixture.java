package com.testebancooriginal.peopleservice.fixture;

import com.testebancooriginal.peopleservice.entity.Address;

public class AddressFixture {

    public static Address getAddress() {
        return Address
            .builder()
            .street("Av Presidente João Goulart")
            .complement("Arizona 98")
            .neighborhood("Umuarama")
            .zipCode("06036048")
            .city("Osasco")
            .state("SP")
            .build();
    }

}
