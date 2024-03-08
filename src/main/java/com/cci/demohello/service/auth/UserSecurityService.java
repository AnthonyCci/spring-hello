package com.cci.demohello.service.auth;

import com.cci.demohello.exception.BadRequestException;
import com.cci.demohello.exception.ResourceNotFounException;
import com.cci.demohello.persistence.entity.User;
import com.cci.demohello.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService {
    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        if (!username.isEmpty() || !username.isBlank()) {
            User userEntity = userRepository.findByUsername(username).orElseThrow(() -> {
                throw new ResourceNotFounException("User not found with username: ".concat(username));
            });
            return userEntity;
        }
        throw new BadRequestException("Bad request: Can't find the user");

    }
}
