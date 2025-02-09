package com.petclinic.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("SELECT o FROM Owner o LEFT JOIN FETCH o.pets WHERE o.firstName = :firstName")
    public Optional<Owner> findByFirstNameWithPets(String firstName);

    public Optional<Owner> findByFirstName(String firstName);
}
