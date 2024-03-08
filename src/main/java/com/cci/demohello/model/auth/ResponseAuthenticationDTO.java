package com.cci.demohello.model.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseAuthenticationDTO implements Serializable {
    private final String token;
    private final String refreshToken;
}
