package com.cci.demohello.config.security.handler;

import com.cci.demohello.exception.AuthenticationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        resolver.resolveException(request, response, null, new AuthenticationException("Unauthorized"));
    }
}
