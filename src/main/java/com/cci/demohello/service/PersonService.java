/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.service;

import com.cci.demohello.model.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Anthony Flores Boza
 */
public interface PersonService {

    public PersonDTO insert(PersonDTO person);

    public PersonDTO update(PersonDTO person);

    public PersonDTO delete(Long person);

    public PersonDTO findById(Long id);

    public PersonDTO findByName(String name);

    public Page<PersonDTO> findAll(Pageable pageable);

}
