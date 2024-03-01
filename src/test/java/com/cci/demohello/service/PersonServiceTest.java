/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.service;

import com.cci.demohello.exception.BadRequestException;
import com.cci.demohello.exception.ResourceNotFounException;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;

import com.cci.demohello.model.PersonDTO;
import com.cci.demohello.persistence.entity.Person;
import com.cci.demohello.persistence.repository.PersonRepository;
import com.cci.demohello.service.impl.PersonServiceImpl;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * @author Anthony Flores Boza
 */
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    private PersonDTO person;

    @Mock
    private PersonRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private PersonServiceImpl service;

    @BeforeEach
    void setup() {
        //Given
        person = new PersonDTO();
        person.setName("PersonDTO of test");
    }

    @Test
    @Transactional
    @Sql(scripts = "/scripts/createSchemaTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testInsertPerson() {
        //Given
        Person personEntity = new Person();
        personEntity.setName(person.getName());

        given(mapper.map(person, new TypeToken<Person>() {
        }.getType())).willReturn(personEntity);

        given(repository.save(personEntity)).willReturn(personEntity);

        given(mapper.map(personEntity, new TypeToken<PersonDTO>() {
        }.getType())).willReturn(person);

        PersonDTO personInserted = service.insert(person);

        verify(mapper, times(1)).map(any(PersonDTO.class), eq(new TypeToken<Person>() {
        }.getType()));

        verify(repository, times(1)).save(any(Person.class));

        verify(mapper, times(1)).map(any(Person.class), eq(new TypeToken<PersonDTO>() {
        }.getType()));

        assertThat(personInserted).isNotNull();
    }

    @Test
    void testInsertPersonWithThrow() {
        PersonDTO personWithThrow = new PersonDTO();
        personWithThrow.setName("");

        assertThrows(BadRequestException.class, () -> {
            service.insert(personWithThrow);
        });

        verify(repository, never()).save(any(Person.class));
    }

    @Test
    void testUpdatePerson() {
        PersonDTO personToUpdate = new PersonDTO();
        personToUpdate.setId(1L);
        personToUpdate.setName("PERSON TO UPDATE");

        Person personEntity = new Person();
        personEntity.setId(personToUpdate.getId());
        personEntity.setName(personToUpdate.getName());

        given(repository.existsById(personToUpdate.getId())).willReturn(true);

        given(mapper.map(personToUpdate, new TypeToken<Person>() {
        }.getType())).willReturn(personEntity);

        given(repository.save(personEntity)).willReturn(personEntity);

        given(mapper.map(personEntity, new TypeToken<PersonDTO>() {
        }.getType())).willReturn(personToUpdate);

        PersonDTO personUpdated = service.update(personToUpdate);

        verify(repository, times(1)).existsById(personToUpdate.getId());

        assertThat(personUpdated).isNotNull();

    }

    @Test
    void testUpdatePersonWithThrow() {
        PersonDTO personToUpdate = new PersonDTO();
        personToUpdate.setId(1L);
        personToUpdate.setName("PERSON TO UPDATE");

        Person personEntity = new Person();
        personEntity.setId(personToUpdate.getId());
        personEntity.setName(personToUpdate.getName());

        given(repository.existsById(personToUpdate.getId())).willReturn(false);

        assertThrows(ResourceNotFounException.class, () -> {
            service.update(personToUpdate);
        });

        verify(repository, times(1)).existsById(personToUpdate.getId());
        verify(repository, never()).save(any(Person.class));

    }

    @Test
    void testFindAllPersons() {
        List<Person> entities = new ArrayList<>();
        given(repository.findAll()).willReturn(entities);

        given(mapper.map(entities, new TypeToken<List<PersonDTO>>() {
        }.getType())).willReturn(List.of());

        List<PersonDTO> personsFound = service.findAll();
        assertThat(personsFound).isNotNull();
        assertThat(personsFound.size()).isEqualTo(0);
    }

}
