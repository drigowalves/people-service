package com.testebancooriginal.peopleservice.fixture;

import com.testebancooriginal.peopleservice.data.request.PersonRequest;

public class PersonRequestFixture {

    public static PersonRequest getPersonRequest() {
        return PersonRequest
            .builder()
            .name("Rodrigo")
            .surname("Assunção")
            .cpf("00000001406")
            .age(28)
            .zipCode("06036048")
            .build();
    }

}
