package com.petclinic.core;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    public Optional<Visit> findByReferenceNumber(String referenceNumber);
}
