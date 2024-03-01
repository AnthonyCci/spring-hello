/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.controller;

import com.cci.demohello.exception.ResourceNotFounException;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.cci.demohello.model.PersonDTO;
import com.cci.demohello.service.impl.PersonServiceImpl;
import static com.cci.demohello.util.JSONParser.parseObjectToJSON;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 *
 * @author Anthony Flores Boza
 */
@WebMvcTest
public class PersonControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public PersonControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @MockBean
    private PersonServiceImpl service;

    @Test
    void testInsertPerson() throws Exception {
        PersonDTO person = new PersonDTO();
        person.setName("New Person");

        given(service.insert(any(PersonDTO.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(parseObjectToJSON(person))
        );

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(person.getId())))
                .andExpect(jsonPath("$.name", is(person.getName())));
    }

    @Test
    public void testFindAllPersons() throws Exception {

        List<PersonDTO> persons = new ArrayList<>();
        persons.add(new PersonDTO(1L, "User: 1"));
        persons.add(new PersonDTO(2L, "User: 2"));
        persons.add(new PersonDTO(3L, "User: 3"));
        persons.add(new PersonDTO(4L, "User: 4"));

        given(service.findAll()).willReturn(persons);

        ResultActions response = mockMvc.perform(
                get("/person")
        );

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(persons.size())));
    }

    @Test
    public void testFindPersonByIdNotFound() throws Exception {

        given(service.findById(1L)).willThrow(ResourceNotFounException.class);

        ResultActions response = mockMvc.perform(
                get("/person/{id}/ById", 1L)
        );

        response.andExpect(status().isNotFound())
                .andDo(print());
    }
}
