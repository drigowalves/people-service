package com.testebancooriginal.peopleservice.repository;


import com.testebancooriginal.peopleservice.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {

    Optional<Person> findByCpf(String cpf);

}
