package com.cci.demohello.service;

import com.cci.demohello.model.UserDTO;

public interface UserService {
    public UserDTO insert(UserDTO user);
    public UserDTO findByUsername(String username);
}
