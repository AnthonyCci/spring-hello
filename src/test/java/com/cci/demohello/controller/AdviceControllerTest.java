package com.cci.demohello.controller;

import com.cci.demohello.exception.config.GlobalExceptionHandler;
import com.cci.demohello.model.ResponseExceptionDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AdviceControllerTest {

    @Test
    public void testHandleException() {
        // Mock de HttpServletRequest y HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Instancia del controlador de excepciones
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        // Lanzar una excepción dentro del método handlerGenericException
        Exception exception = new Exception("Test exception");
        ResponseEntity<ResponseExceptionDTO> responseEntity = globalExceptionHandler.handlerGenericException(exception);

        // Verificar que se genera una respuesta con el código de estado correcto y el mensaje de la excepción
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus());
        assertEquals("Test exception", responseEntity.getBody().getMessage());
    }
}
