package com.jaravir.personsapi.controller;

import com.jaravir.personsapi.controller.exceptions.PersonAlreadyExistException;
import com.jaravir.personsapi.controller.exceptions.PersonNotFoundException;
import com.jaravir.personsapi.converter.PersonEntityConverter;
import com.jaravir.personsapi.entity.Person;
import com.jaravir.personsapi.record.AddressRecord;
import com.jaravir.personsapi.record.PersonListRecord;
import com.jaravir.personsapi.record.PersonRecord;
import com.jaravir.personsapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    PersonEntityConverter converter = new PersonEntityConverter();

    @GetMapping("/persons")
    public PersonListRecord getPersons(@RequestParam Optional<String> firstName, @RequestParam Optional<String> lastName) {
        List<Person> persons;
        if (firstName.isPresent() && lastName.isPresent()) {
            persons = Collections.singletonList(personRepository.findByFirstNameAndLastName(firstName.get(), lastName.get()).orElseThrow(() -> new PersonNotFoundException(firstName.get(), lastName.get())));
        } else if (firstName.isPresent()) {
            persons = personRepository.findByFirstName(firstName.get());
        } else if (lastName.isPresent()) {
            persons = personRepository.findByLastName(lastName.get());
        } else {
            persons = personRepository.findAll();
        }
        List<PersonRecord> personRecords = persons.stream().map(converter::toRecord).collect(Collectors.toList());
        return new PersonListRecord(personRecords);
    }

    @PostMapping("/persons")
    public PersonRecord createPerson(@RequestBody PersonRecord record) {
        Person person = converter.toPerson(record);
        if (personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName()).isPresent()) {
            throw new PersonAlreadyExistException(person.getFirstName(), person.getLastName());
        }
        person = personRepository.save(person);
        return converter.toRecord(person);
    }

    @GetMapping("/persons/{personId}")
    public PersonRecord getPersonById(@PathVariable long personId) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
        return converter.toRecord(person);
    }

    @PutMapping("/persons/{personId}")
    public void updateAddress(@PathVariable Long personId, @RequestBody AddressRecord newAddress) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
        person.setCurrentAddress(newAddress.currentAddress());
        personRepository.save(person);
    }
}
