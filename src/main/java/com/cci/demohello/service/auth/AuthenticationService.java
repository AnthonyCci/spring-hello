package com.cci.demohello.service.auth;

import com.cci.demohello.exception.BadCredentialsException;
import com.cci.demohello.model.auth.RequestAuthenticationDTO;
import com.cci.demohello.model.auth.ResponseAuthenticationDTO;
import com.cci.demohello.persistence.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private final UserSecurityService userService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserSecurityService userService, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseAuthenticationDTO login(RequestAuthenticationDTO authenticationRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );
        try {
            authenticationManager.authenticate(authentication);
        } catch (org.springframework.security.authentication.BadCredentialsException ex) {
            throw new BadCredentialsException("Bad credentials");
        }

        User userFound = userService.findByUsername(authenticationRequest.getUsername());

        String jwt = jwtService.generateJWT(userFound, generateExtraClaims(userFound));

        return new ResponseAuthenticationDTO(jwt, jwt);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole());
        extraClaims.put("authorities", user.getAuthorities());
        return extraClaims;
    }

}
