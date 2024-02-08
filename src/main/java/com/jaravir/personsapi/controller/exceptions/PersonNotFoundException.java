package com.jaravir.personsapi.controller.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long personId) {
        super("Failed to find Person with ID " + personId);
    }

    public PersonNotFoundException(String firstName, String lastName) {
        super("Failed to find Person with firstName " + firstName + ", lastName " + lastName);
    }
}
