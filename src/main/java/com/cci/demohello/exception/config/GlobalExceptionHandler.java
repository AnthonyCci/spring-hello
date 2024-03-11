/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.exception.config;

import com.cci.demohello.exception.*;
import com.cci.demohello.model.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Anthony Flores Boza
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseExceptionDTO> handlerGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseExceptionDTO> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseExceptionDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFounException.class)
    public ResponseEntity<ResponseExceptionDTO> handleResourceNotFoundException(ResourceNotFounException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseExceptionDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(
            org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<ResponseExceptionDTO> handlerAccessDeniedSpringSecurityException(org.springframework.security.access.AccessDeniedException ex) {
        AccessDeniedException exceptionResponse = new AccessDeniedException("Access denied");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseExceptionDTO(HttpStatus.FORBIDDEN.value(), exceptionResponse.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseExceptionDTO> handlerAccessDeniedLocalException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseExceptionDTO(HttpStatus.FORBIDDEN.value(), ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseExceptionDTO> handlerAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseExceptionDTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseExceptionDTO> handlerConflictException(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseExceptionDTO(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseExceptionDTO> handlerBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseExceptionDTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
    }

}
