package com.cci.demohello.service.auth;

import com.cci.demohello.exception.ResourceNotFounException;
import com.cci.demohello.persistence.entity.User;
import com.cci.demohello.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository repository;

    public UserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user
                = repository.findByUsername(username).orElseThrow(() -> {
            throw new ResourceNotFounException("User not found with username: ".concat(username));
        });
        return user;
    }
}
