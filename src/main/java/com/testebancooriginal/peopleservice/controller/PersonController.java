package com.testebancooriginal.peopleservice.controller;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.fromString;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.testebancooriginal.peopleservice.data.request.PersonRequest;
import com.testebancooriginal.peopleservice.data.response.PersonResponse;
import com.testebancooriginal.peopleservice.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
@AllArgsConstructor
@Api("Endpoints to manage people")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Create a person", response = PersonResponse.class)
    public PersonResponse create(@RequestBody @Valid PersonRequest personRequest) {
        return personService.create(personRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find one person by id", response = PersonResponse.class)
    public PersonResponse findById(@PathVariable("id") String id) {
        return personService.findById(id);
    }

    @GetMapping
    @ApiOperation(value = "List all available people", response = Page.class)
    public Page<PersonResponse> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "limit", defaultValue = "12") int limit,
        @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy) {
        return personService.findAll(of(page, limit, Sort.by(fromString(orderBy), "name")));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Delete person")
    public void delete(@PathVariable("id") String id) {
        personService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update one person", response = PersonResponse.class)
    public PersonResponse update(
                @PathVariable("id") String id,
                @RequestBody PersonRequest personRequest) {
        return personService.update(id, personRequest);
    }

}
