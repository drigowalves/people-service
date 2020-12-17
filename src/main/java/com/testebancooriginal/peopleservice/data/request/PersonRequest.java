package com.testebancooriginal.peopleservice.data.request;

import com.testebancooriginal.peopleservice.entity.Person;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonRequest {

    @NotBlank(message = "name should not be blank")
    private String name;
    @NotBlank(message = "surname should not be blank")
    private String surname;
    @NotNull(message = "age should not be null")
    private Integer age;
    @NotBlank(message = "zipCode should not be blank")
    private String zipCode;
    @NotBlank(message = "cpf should not be blank")
    private String cpf;

    public Person toPersonDomain() {
        return Person.builder()
                .name(name)
                .age(age)
                .cpf(cpf)
                .surname(surname)
                .build();
    }

}
