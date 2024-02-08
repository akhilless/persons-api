package com.jaravir.personsapi.converter;

import com.jaravir.personsapi.entity.Person;
import com.jaravir.personsapi.record.PersonRecord;

public class PersonEntityConverter {
    public PersonRecord toRecord(Person person) {
        return new PersonRecord(person.getId(), person.getFirstName(), person.getLastName(), person.getBirthDate(), person.getCurrentAddress());
    }

    public Person toPerson(PersonRecord record) {
        return new Person(record.firstName(), record.lastName(), record.birthDate(), record.currentAddress());
    }
}
