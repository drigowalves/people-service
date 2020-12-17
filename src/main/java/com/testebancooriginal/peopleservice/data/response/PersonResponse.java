package com.testebancooriginal.peopleservice.data.response;

import com.testebancooriginal.peopleservice.entity.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {

    private String id;
    private String name;
    private String surname;
    private Integer age;
    private Address address;
    private String cpf;

}
