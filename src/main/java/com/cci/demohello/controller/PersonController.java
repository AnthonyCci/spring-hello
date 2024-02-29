/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.controller;

import com.cci.demohello.model.PersonDTO;
import com.cci.demohello.service.impl.PersonServiceImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anthony Flores Boza
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonServiceImpl service;

    public PersonController(PersonServiceImpl service) {
        this.service = service;
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(person));
    }

    @PutMapping(path = {"", "/"})
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(person));
    }

    @DeleteMapping(path = {"/{person}/delete"})
    public ResponseEntity<PersonDTO> delete(@PathVariable("person") Long person) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(person));
    }

    @GetMapping(path = {"/{id}/ById"})
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping(path = {"/{name}/ByName"})
    public ResponseEntity<PersonDTO> findByName(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

}
