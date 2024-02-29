/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.persistence;

import com.cci.demohello.persistence.entity.Person;
import com.cci.demohello.persistence.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * @author Anthony Flores Boza
 */
@DataJpaTest
public class PersonRepositoryTest {

    private final PersonRepository repository;

    @Autowired
    public PersonRepositoryTest(PersonRepository repository) {
        this.repository = repository;
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/createSchemaTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testInsertPerson() {
        //Given
        Person person = new Person();
        person.setName("Person of test");

        //When
        Person personInserted = repository.save(person);

        //Then
        Assertions.assertThat(personInserted).isNotNull();
        Assertions.assertThat(personInserted.getId()).isNotNull();
    }

}
