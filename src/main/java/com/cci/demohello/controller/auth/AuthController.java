package com.cci.demohello.controller.auth;

import com.cci.demohello.model.auth.RequestAuthenticationDTO;
import com.cci.demohello.model.auth.ResponseAuthenticationDTO;
import com.cci.demohello.service.auth.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping(path = {"/login"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll")
    public ResponseEntity<?> login(@RequestBody RequestAuthenticationDTO user) {
        ResponseAuthenticationDTO response = authenticationService.login(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
