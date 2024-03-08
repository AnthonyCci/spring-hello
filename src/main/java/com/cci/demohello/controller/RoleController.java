package com.cci.demohello.controller;

import com.cci.demohello.model.RoleDTO;
import com.cci.demohello.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(path = {""}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(role));
    }
}
