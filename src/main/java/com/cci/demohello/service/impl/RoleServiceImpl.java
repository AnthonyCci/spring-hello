package com.cci.demohello.service.impl;

import com.cci.demohello.exception.BadRequestException;
import com.cci.demohello.exception.ConflictException;
import com.cci.demohello.model.RoleDTO;
import com.cci.demohello.persistence.entity.Role;
import com.cci.demohello.persistence.repository.RoleRepository;
import com.cci.demohello.service.RoleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final ModelMapper mapper;

    public RoleServiceImpl(RoleRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RoleDTO insert(RoleDTO role) {
        if (role.getName() != null
                && !role.getName().isEmpty()
                && !role.getName().isBlank()) {
            boolean existsByName = repository.existsByName(role.getName());
            if (!existsByName) {
                Role roleEntity = mapper.map(role, new TypeToken<Role>() {
                }.getType());
                Role roleInserted = repository.save(roleEntity);
                return mapper.map(roleInserted, new TypeToken<RoleDTO>() {
                }.getType());
            }
            throw new ConflictException("Role with name: ".concat(role.getName()) + " already exists");
        }
        throw new BadRequestException("Bad request: Can't insert the person");
    }
}
