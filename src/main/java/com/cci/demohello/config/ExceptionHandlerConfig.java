/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.config;

import com.cci.demohello.exception.BadRequestException;
import com.cci.demohello.exception.ResourceNotFounException;
import com.cci.demohello.model.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Anthony Flores Boza
 */
@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseException> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseException(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFounException.class)
    public ResponseEntity<ResponseException> handleResourceNotFoundException(ResourceNotFounException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseException(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
