package com.jaravir.personsapi.controller.advice;

import com.jaravir.personsapi.controller.exceptions.PersonAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PersonAlreadyExistAdvice {
    @ResponseBody
    @ExceptionHandler(PersonAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String personAlreadyExistHandler(PersonAlreadyExistException ex) {
        return ex.getMessage();
    }
}
