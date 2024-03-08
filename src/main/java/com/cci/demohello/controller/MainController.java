/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anthony Flores Boza
 */
@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping(path = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> hello() {
        Map<String, Object> response = new HashMap<String, Object>() {
            {
                put("message", "Server is running");
                put("date", LocalDate.now());
            }
        };
        return ResponseEntity.ok(response);
    }
}
