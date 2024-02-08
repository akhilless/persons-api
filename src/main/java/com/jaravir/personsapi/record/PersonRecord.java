package com.jaravir.personsapi.record;

import java.time.LocalDate;

public record PersonRecord (long id, String firstName, String lastName, LocalDate birthDate, String currentAddress) {}
