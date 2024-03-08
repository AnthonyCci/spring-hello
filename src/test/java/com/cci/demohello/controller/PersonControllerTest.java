/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.controller;

import com.cci.demohello.config.security.handler.CustomAccessDeniedEntryPoint;
import com.cci.demohello.exception.AccessDeniedException;
import com.cci.demohello.exception.ResourceNotFounException;
import com.cci.demohello.model.PersonDTO;
import com.cci.demohello.service.impl.PersonServiceImpl;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static com.cci.demohello.util.JSONParser.parseObjectToJSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Anthony Flores Boza
 */
@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @MockBean
    private PersonServiceImpl service;

    @Test
    @WithMockUser(username = "user.test", roles = {"ADMINISTRATOR"})
    void testInsertPerson() throws Exception {

        PersonDTO person = new PersonDTO();
        person.setName("New Person");

        given(service.insert(any(PersonDTO.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(
                post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(parseObjectToJSON(person))
        );

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(person.getId())))
                .andExpect(jsonPath("$.name", is(person.getName())));
    }

    @Test
    @WithMockUser(username = "user.test", roles = {"CLIENT_BASIC"})
    void testInsertPersonAccessDenied() throws Exception {

        PersonDTO person = new PersonDTO();
        person.setName("New Person");

        Throwable exception = assertThrows(ServletException.class, () -> {
            mockMvc.perform(
                    post("/persons")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(parseObjectToJSON(person))
            );
        });
        assertTrue(exception.getCause() instanceof org.springframework.security.access.AccessDeniedException, "Access Denied");
    }

   /* @Test
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
    }*/

    @Test
    @WithMockUser(username = "user.test", roles = {"ADMINISTRATOR"})
    public void testFindPersonByIdNotFound() throws Exception {

        given(service.findById(1L)).willThrow(ResourceNotFounException.class);

        ResultActions response = mockMvc.perform(
                get("/persons/{id}/ById", 1L)
        );

        response.andExpect(status().isNotFound())
                .andDo(print());
    }
}