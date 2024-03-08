package com.cci.demohello.persistence.repository;

import com.cci.demohello.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public abstract Optional<Role> findByName(String name);

    public abstract boolean existsByName(String name);
}
