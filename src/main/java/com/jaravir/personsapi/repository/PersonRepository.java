package com.jaravir.personsapi.repository;

import com.jaravir.personsapi.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * retrieve all persons
 * retrieve a signle person by id
 * update person
 * retrieve person by last name
 * retrieve person by firstname
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
    @Query("select p from Person p where p.firstName = :firstName and p.lastName = :lastName")
    Optional<Person> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
    @Override
    List<Person> findAll();
}
