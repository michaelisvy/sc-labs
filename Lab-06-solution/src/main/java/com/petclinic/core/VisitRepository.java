package com.petclinic.core;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
    public Visit findByReferenceNumber(String referenceNumber);
}
