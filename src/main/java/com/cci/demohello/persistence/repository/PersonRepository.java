/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.demohello.persistence.repository;

import com.cci.demohello.persistence.entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anthony Flores Boza
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public abstract Optional<Person> findByName(String name);
}
