package com.testebancooriginal.peopleservice.service;

import static com.testebancooriginal.peopleservice.fixture.AddressFixture.getAddress;
import static com.testebancooriginal.peopleservice.fixture.PersonFixture.getPerson;
import static com.testebancooriginal.peopleservice.fixture.PersonRequestFixture.getPersonRequest;
import static java.util.Optional.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.testebancooriginal.peopleservice.data.request.PersonRequest;
import com.testebancooriginal.peopleservice.data.response.PersonResponse;
import com.testebancooriginal.peopleservice.entity.Address;
import com.testebancooriginal.peopleservice.entity.Person;
import com.testebancooriginal.peopleservice.exception.ConflictException;
import com.testebancooriginal.peopleservice.exception.NotFoundException;
import com.testebancooriginal.peopleservice.fixture.PersonFixture;
import com.testebancooriginal.peopleservice.repository.PersonRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;
    @Mock
    private AddressService addressService;
    @Mock
    private PersonRepository personRepository;

    @Test
    public void shouldCreateAPerson() {
        PersonRequest personRequest = getPersonRequest();
        Address address = getAddress();
        given(personRepository.findByCpf(eq(personRequest.getCpf()))).willReturn(empty());
        given(personRepository.save(any(Person.class)))
            .willReturn(personRequest.toPersonDomain().withAddress(address));
        given(addressService.getAddressByZipCode(personRequest.getZipCode())).willReturn(address);

        PersonResponse personResponse = personService.create(personRequest);

        assertThat(personResponse).isNotNull();
        assertThat(personResponse.getName()).isEqualTo(personRequest.getName());
        assertThat(personResponse.getSurname()).isEqualTo(personRequest.getSurname());
        assertThat(personResponse.getAge()).isEqualTo(personRequest.getAge());
        assertThat(personResponse.getCpf()).isEqualTo(personRequest.getCpf());
        assertThat(personResponse.getAddress().getStreet()).isEqualTo(address.getStreet());
        assertThat(personResponse.getAddress().getComplement()).isEqualTo(address.getComplement());
        assertThat(personResponse.getAddress().getNeighborhood()).isEqualTo(address.getNeighborhood());
        assertThat(personResponse.getAddress().getCity()).isEqualTo(address.getCity());
        assertThat(personResponse.getAddress().getState()).isEqualTo(address.getState());
        assertThat(personResponse.getAddress().getZipCode()).isEqualTo(address.getZipCode());
    }

    @Test
    public void shouldThrowConflictException_WhenPersonCpfAlreadyExists() {
        PersonRequest personRequest = getPersonRequest();
        given(personRepository.findByCpf(eq(personRequest.getCpf())))
            .willReturn(of(personRequest.toPersonDomain()));

        assertThrows(ConflictException.class, () -> personService.create(personRequest));
    }

    @Test
    public void shouldFindAPersonById() {
        Person person = getPerson();
        given(personRepository.findById(eq(person.getId()))).willReturn(Optional.of(person));

        PersonResponse personResponse = personService.findById(person.getId());

        assertThat(personResponse.getId()).isNotBlank().isEqualTo(person.getId());
        assertThat(personResponse.getName()).isNotBlank().isEqualTo(person.getName());
        assertThat(personResponse.getSurname()).isNotBlank().isEqualTo(person.getSurname());
        assertThat(personResponse.getAge()).isNotNull().isEqualTo(person.getAge());
        assertThat(personResponse.getAddress()).isNotNull();
        assertThat(personResponse.getAddress().getStreet()).isEqualTo(person.getAddress().getStreet());
        assertThat(personResponse.getAddress().getComplement()).isEqualTo(person.getAddress().getComplement());
        assertThat(personResponse.getAddress().getNeighborhood()).isEqualTo(person.getAddress().getNeighborhood());
        assertThat(personResponse.getAddress().getCity()).isEqualTo(person.getAddress().getCity());
        assertThat(personResponse.getAddress().getState()).isEqualTo(person.getAddress().getState());
        assertThat(personResponse.getAddress().getZipCode()).isEqualTo(person.getAddress().getZipCode());


    }

    @Test
    public void shouldThrowNotFoundException_WhenTryPersonIdNotFound() {
        Person person = getPerson();
        given(personRepository.findById(eq(person.getId()))).willReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> personService.findById(person.getId()));
    }

    @Test
    public void shouldUpdateAPerson() {
        Person person = getPerson();
        Address address = getAddress();
        given(personRepository.findById(eq(person.getId()))).willReturn(of(person));
        given(personRepository.save(any(Person.class)))
            .willReturn(person.withAge(29));

        PersonResponse personResponse = personService
            .update(person.getId(), person.withAge(29).toPersonRequest());

        assertThat(personResponse.getId()).isNotBlank().isEqualTo(person.getId());
        assertThat(personResponse.getName()).isNotBlank().isEqualTo(person.getName());
        assertThat(personResponse.getSurname()).isNotBlank().isEqualTo(person.getSurname());
        assertThat(personResponse.getAge()).isNotNull().isNotEqualTo(person.getAge());
        assertThat(personResponse.getCpf()).isNotBlank().isEqualTo(person.getCpf());
    }

    @Test
    public void shouldThrowNotFound_WhenTryUpdateAPerson() {
        Person person = getPerson();
        given(personRepository.findById(eq(person.getId()))).willReturn(empty());
        assertThrows(NotFoundException.class, () -> personService.update(person.getId(), person.toPersonRequest()));
    }

    @Test
    public void shouldDeleteAPerson() {
        Person person = getPerson();
        given(personRepository.save(any(Person.class)))
            .willReturn(person);
        PersonResponse personResponse = personService.create(person.toPersonRequest());
        given(personRepository.findById(eq(personResponse.getId()))).willReturn(of(person));
        assertDoesNotThrow(() -> personService.delete(personResponse.getId()));
    }

    @Test
    public void shouldThrowNotFound_WhenTryDeleteAPerson() {
        Person person = getPerson();
        given(personRepository.save(any(Person.class)))
            .willReturn(person);
        PersonResponse personResponse = personService.create(person.toPersonRequest());
        given(personRepository.findById(eq(personResponse.getId()))).willReturn(empty());
        assertThrows(NotFoundException.class, () -> personService.delete(personResponse.getId()));
    }

}