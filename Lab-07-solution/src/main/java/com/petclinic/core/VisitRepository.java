package com.petclinic.core;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    public Visit findByReferenceNumber(String referenceNumber);
}
