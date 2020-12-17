package com.testebancooriginal.peopleservice.service;

import com.testebancooriginal.peopleservice.data.request.PersonRequest;
import com.testebancooriginal.peopleservice.data.response.PersonResponse;
import com.testebancooriginal.peopleservice.entity.Person;
import com.testebancooriginal.peopleservice.exception.ConflictException;
import com.testebancooriginal.peopleservice.exception.NotFoundException;
import com.testebancooriginal.peopleservice.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressService addressService;

    public PersonResponse create(PersonRequest personRequest) {
        personRepository.findByCpf(personRequest.getCpf()).ifPresent(person -> {
            throw new ConflictException(format("person cpf=%s already exist", person.getCpf()));
        });

        return personRepository
            .save(personRequest
                .toPersonDomain()
                .withAddress(addressService.getAddressByZipCode(personRequest.getZipCode())))
            .toPersonResponse();
    }

    public PersonResponse findById(String id) {
        return getPersonById(id)
            .toPersonResponse();
    }

    private Person getPersonById(String id) {
        return personRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(format("person id=%s not found", id)));
    }

    public PersonResponse findByCpf(String cpf) {
        return personRepository.findByCpf(cpf)
            .orElseThrow(() -> new NotFoundException(format("person cpf=%s not found", cpf)))
            .toPersonResponse();
    }

    public Page<PersonResponse> findAll(Pageable pageable) {
        return personRepository.findAll(pageable).map(Person::toPersonResponse);
    }

    public PersonResponse update(String id, PersonRequest personRequest) {
        getPersonById(id);
        return personRepository.save(personRequest.toPersonDomain().withId(id)).toPersonResponse();
    }

    public void delete(String id) {
        getPersonById(id);
        personRepository.deleteById(id);
    }

}
