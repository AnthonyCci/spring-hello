/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import com.cci.demohello.persistence.entity.Person;
import com.cci.demohello.persistence.repository.PersonRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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
    private Person person;

    @Autowired
    public PersonRepositoryTest(PersonRepository repository) {
        this.repository = repository;
    }

    @BeforeEach
    void setup() {
        person = new Person();
        person.setName("Person of test");
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/createSchemaTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testInsertPerson() {
        //Given
        //Person person = new Person()
        //person.setName("Person of test");

        //When
        Person personInserted = repository.save(person);

        //Then
        assertThat(personInserted).isNotNull();
        assertThat(personInserted.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/createSchemaTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testUpdatePerson() {
        //Given
        //Person person = new Person();
        //person.setName("Person of test");

        //When
        Person personInserted = repository.save(person);

        //Then
        assertThat(personInserted).isNotNull();
        assertThat(personInserted.getId()).isNotNull();

        personInserted.setName("Person test update");
        Person personUpdated = repository.save(personInserted);
        assertThat(personUpdated.getId()).isEqualTo(personInserted.getId());
        assertThat(personUpdated.getName()).isEqualTo("Person test update");
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/createSchemaTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testDeletePerson() {
        //Given
        //Person person = new Person();
        //person.setName("Person of test");

        //When
        Person personInserted = repository.save(person);

        //Then
        assertThat(personInserted).isNotNull();
        assertThat(personInserted.getId()).isNotNull();

        Long idPerson = personInserted.getId();
        repository.deleteById(idPerson);

        Person personFound = repository.findById(idPerson).orElse(null);
        assertThat(personFound).isNull();
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/createSchemaTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testFindByIdPerson() {
        //Given
        //Person person = new Person();
        //person.setName("Person of test");

        //When
        Person personInserted = repository.save(person);

        //Then
        assertThat(personInserted).isNotNull();
        assertThat(personInserted.getId()).isNotNull();

        Person personFound = repository.findById(personInserted.getId()).orElse(null);
        assertThat(personFound).isNotNull();
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/createSchemaTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testFindAllPersons() {
        //Given
        for (int i = 0; i < 5; i++) {
            Person personToInsert = new Person();
            person.setName("Person of test => " + (i + 1));

            //When
            Person personInserted = repository.save(personToInsert);

            //Then
            assertThat(personInserted).isNotNull();
            assertThat(personInserted.getId()).isNotNull();
        }

        List<Person> persons = repository.findAll();
        assertThat(persons).isNotNull();
        assertThat(persons).asList().isNotEmpty();
        assertThat(persons).hasSize(5);
    }
}
