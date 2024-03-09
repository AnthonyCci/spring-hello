package com.cci.demohello.controller;

import com.cci.demohello.exception.BadRequestException;
import com.cci.demohello.exception.config.GlobalExceptionHandler;
import com.cci.demohello.model.ResponseExceptionDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AdviceControllerTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private GlobalExceptionHandler adviceController;

    @Test
    public void testHandleException() {
        Exception ex = new Exception("Test exception");
        ResponseEntity<ResponseExceptionDTO> response = adviceController.handlerGenericException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode().value());
        assertEquals("Test exception", response.getBody().getMessage());
    }

    @Test
    public void testHandleBadRequestException() {
        BadRequestException ex = new BadRequestException("Test exception");
        ResponseEntity<ResponseExceptionDTO> response = adviceController.handleBadRequestException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode().value());
        assertEquals("Test exception", response.getBody().getMessage());
    }
}
