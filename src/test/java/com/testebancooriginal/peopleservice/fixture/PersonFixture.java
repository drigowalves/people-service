package com.testebancooriginal.peopleservice.fixture;

import com.testebancooriginal.peopleservice.entity.Address;
import com.testebancooriginal.peopleservice.entity.Person;

public class PersonFixture {

    public static Person getPerson() {
        return Person
            .builder()
            .name("Rodrigo")
            .surname("Assunção")
            .age(28)
            .cpf("00000001406")
            .id("31fdsf123")
            .address(AddressFixture.getAddress())
            .build();
    }

}
