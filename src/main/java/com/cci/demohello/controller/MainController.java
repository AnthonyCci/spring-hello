/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anthony Flores Boza
 */
@RestController
@RequestMapping("/hello-world")
public class MainController {

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> hello() {
        Map<String, Object> response = new HashMap<String, Object>() {
            {
                put("message", "Hello World Anthony");
                put("date", LocalDate.now());
            }
        };
        return ResponseEntity.ok(response);
    }
}
