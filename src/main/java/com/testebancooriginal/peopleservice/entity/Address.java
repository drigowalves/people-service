package com.testebancooriginal.peopleservice.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String zipCode;
    private String state;

}
