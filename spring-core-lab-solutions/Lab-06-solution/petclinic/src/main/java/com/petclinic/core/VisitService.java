package com.petclinic.core;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit findById(Integer id) {
        Optional<Visit> visit = this.visitRepository.findById(id);
        if(visit.isEmpty()) {
            throw new EntityNotFoundException("entity " +id + " was not found");
        }
        else return visit.get();
    }

    public Visit findByReferenceNumber(String referenceNumber) {
        return visitRepository.findByReferenceNumber(referenceNumber);
    }

    public void delete(Visit visit) {
        this.visitRepository.delete(visit);
    }

    public void save(Visit visit) {
        this.visitRepository.save(visit);
    }
}
