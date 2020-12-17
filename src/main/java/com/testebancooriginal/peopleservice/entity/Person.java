package com.testebancooriginal.peopleservice.entity;

import com.testebancooriginal.peopleservice.data.request.PersonRequest;
import com.testebancooriginal.peopleservice.data.response.PersonResponse;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
@With
public class Person {

    @Id
    private String id;
    private String name;
    private String surname;
    private Integer age;
    private Address address;

    @Indexed
    private String cpf;

    public PersonResponse toPersonResponse() {
        return PersonResponse
                .builder()
                .id(id)
                .address(address)
                .age(age)
                .cpf(cpf)
                .name(name)
                .surname(surname)
                .build();
    }

    public PersonRequest toPersonRequest() {
        return PersonRequest
            .builder()
            .age(age)
            .cpf(cpf)
            .name(name)
            .surname(surname)
            .build();
    }

}
