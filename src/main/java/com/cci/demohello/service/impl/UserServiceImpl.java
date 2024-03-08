package com.cci.demohello.service.impl;

import com.cci.demohello.exception.BadRequestException;
import com.cci.demohello.exception.ConflictException;
import com.cci.demohello.exception.ResourceNotFounException;
import com.cci.demohello.model.UserDTO;
import com.cci.demohello.persistence.entity.User;
import com.cci.demohello.persistence.repository.UserRepository;
import com.cci.demohello.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO insert(UserDTO user) {
        if (user.getUsername() != null
                && !user.getUsername().isEmpty()
                && !user.getUsername().isBlank()
                && user.getName() != null
                && !user.getName().isEmpty()
                && !user.getName().isBlank()
                && user.getPassword() != null
                && !user.getPassword().isEmpty()
                && !user.getPassword().isBlank()) {

            boolean existsByUsername = repository.existsByUsername(user.getUsername());
            if (!existsByUsername) {
                String passwordEncoded = passwordEncoder.encode(user.getPassword());
                user.setPassword(passwordEncoded);
                User userEntity = mapper.map(user, new TypeToken<User>() {
                }.getType());
                User userInserted = repository.save(userEntity);
                return mapper.map(userInserted, new TypeToken<UserDTO>() {
                }.getType());
            }
            throw new ConflictException("User with username: ".concat(user.getUsername()) + " already exists");

        }
        throw new BadRequestException("Bad request: Can't insert the user");
    }

    @Override
    public UserDTO findByUsername(String username) {
        if (!username.isEmpty() ||
                !username.isBlank()) {
            User userEntity = repository.findByUsername(username).orElseThrow(() -> {
                throw new ResourceNotFounException("User not found with username: ".concat(username));
            });
            return mapper.map(userEntity, new TypeToken<UserDTO>() {
            }.getType());
        }
        throw new BadRequestException("Bad request: Can't find the user");
    }
}
