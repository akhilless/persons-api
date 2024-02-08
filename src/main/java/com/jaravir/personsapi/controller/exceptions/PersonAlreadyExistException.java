package com.jaravir.personsapi.controller.exceptions;

public class PersonAlreadyExistException extends RuntimeException {
    public PersonAlreadyExistException(String firstName, String lastName) {
        super("Person with firstName " + firstName + " and lastName " + lastName + " already exists.");
    }
}
