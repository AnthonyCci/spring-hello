package com.cci.demohello.model.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestAuthenticationDTO implements Serializable {

    private final String username;
    private final String password;


}
