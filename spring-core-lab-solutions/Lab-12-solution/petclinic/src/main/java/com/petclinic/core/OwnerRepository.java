package com.petclinic.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    @Query("SELECT o FROM Owner o LEFT JOIN FETCH o.pets WHERE o.firstName = :firstName")
    public Owner findByFirstNameWithPets(String firstName);

    public Owner findByFirstName(String firstName);
}
