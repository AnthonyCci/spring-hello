/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.service.impl;

import com.cci.demohello.exception.BadRequestException;
import com.cci.demohello.exception.ResourceNotFounException;
import com.cci.demohello.model.PersonDTO;
import com.cci.demohello.persistence.entity.Person;
import com.cci.demohello.persistence.repository.PersonRepository;
import com.cci.demohello.service.PersonService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Anthony Flores Boza
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final ModelMapper mapper;
    private final PersonRepository repository;

    public PersonServiceImpl(ModelMapper mapper, PersonRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional
    public PersonDTO insert(PersonDTO person) {
        if (person.getId() == null && person.getName() != null
                && !person.getName().isEmpty()
                && !person.getName().isBlank()) {
            Person personEntity = mapper.map(person, new TypeToken<Person>() {
            }.getType());
            Person personInserted = repository.save(personEntity);
            return mapper.map(personInserted, new TypeToken<PersonDTO>() {
            }.getType());
        }
        throw new BadRequestException("Bad request: Can't insert the person");
    }

    @Override
    @Transactional
    public PersonDTO update(PersonDTO person) {

        boolean personExists = repository.existsById(person.getId());

        if (!personExists) {
            throw new ResourceNotFounException("Not found: Person don't exists");
        }

        if (person.getId() != null && person.getName() != null
                && !person.getName().isEmpty()
                && !person.getName().isBlank()) {
            Person personEntity = mapper.map(person, new TypeToken<Person>() {
            }.getType());
            Person personUpdated = repository.save(personEntity);
            return mapper.map(personUpdated, new TypeToken<PersonDTO>() {
            }.getType());
        }
        throw new BadRequestException("Bad request: Can't update the person");
    }

    @Override
    @Transactional
    public PersonDTO delete(Long person) {
        Person personEntity = repository.findById(person).orElseThrow(() -> {
            throw new ResourceNotFounException("Not found: Person don't exists");
        });
        repository.deleteById(personEntity.getId());
        PersonDTO personDeleted = mapper.map(personEntity, new TypeToken<PersonDTO>() {
        }.getType());
        return personDeleted;
    }

    @Override
    public PersonDTO findById(Long id) {
        Person personEntity = repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFounException("Not found: Person don't exists");
        });
        PersonDTO personFound = mapper.map(personEntity, new TypeToken<PersonDTO>() {
        }.getType());
        return personFound;
    }

    @Override
    public PersonDTO findByName(String name) {
        Person personEntity = repository.findByName(name).orElseThrow(() -> {
            throw new ResourceNotFounException("Not found: Person don't exists");
        });
        PersonDTO personFound = mapper.map(personEntity, new TypeToken<PersonDTO>() {
        }.getType());
        return personFound;
    }

    @Override
    public Page<PersonDTO> findAll(Pageable pageable) {
        Page<Person> personsPage = repository.findAll(pageable);
        return mapper.map(personsPage, new TypeToken<Page<PersonDTO>>() {
        }.getType());
    }

}
