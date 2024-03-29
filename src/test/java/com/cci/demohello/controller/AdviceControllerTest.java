package com.cci.demohello.controller;

import com.cci.demohello.exception.*;
import com.cci.demohello.exception.config.GlobalExceptionHandler;
import com.cci.demohello.model.ResponseExceptionDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;

public class AdviceControllerTest {

    @Test
    public void testHandlerGenericException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        Exception exception = new Exception("Test exception");
        ResponseEntity<ResponseExceptionDTO> responseEntity = globalExceptionHandler.handlerGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus());
        assertEquals("Test exception", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandleBadRequestException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        BadRequestException exception = new BadRequestException("Test exception");
        ResponseEntity<ResponseExceptionDTO> responseEntity = globalExceptionHandler.handleBadRequestException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
        assertEquals("Test exception", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandleResourceNotFoundException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        ResourceNotFounException exception = new ResourceNotFounException("Test exception");
        ResponseEntity<ResponseExceptionDTO> responseEntity = globalExceptionHandler.handleResourceNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatus());
        assertEquals("Test exception", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandlerAccessDeniedException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        AccessDeniedException exception = new AccessDeniedException("Test exception");
        ResponseEntity<ResponseExceptionDTO> responseEntity = globalExceptionHandler.handlerAccessDeniedLocalException(exception);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        assertEquals(HttpStatus.FORBIDDEN.value(), responseEntity.getBody().getStatus());
        assertEquals("Test exception", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandlerAuthenticationException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        AuthenticationException exception = new AuthenticationException("Test exception");
        ResponseEntity<ResponseExceptionDTO> responseEntity = globalExceptionHandler.handlerAuthenticationException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getBody().getStatus());
        assertEquals("Test exception", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandlerConflictException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        ConflictException exception = new ConflictException("Test exception");
        ResponseEntity<ResponseExceptionDTO> responseEntity = globalExceptionHandler.handlerConflictException(exception);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getBody().getStatus());
        assertEquals("Test exception", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandlerBadCredentialsException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        BadCredentialsException exception = new BadCredentialsException("Test exception");
        ResponseEntity<ResponseExceptionDTO> responseEntity = globalExceptionHandler.handlerBadCredentialsException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getBody().getStatus());
        assertEquals("Test exception", responseEntity.getBody().getMessage());
    }
}
