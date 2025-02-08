package com.petclinic.core;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner findByFirstName(String firstName) {
        var owner = ownerRepository.findByFirstName(firstName);
        if (owner.isEmpty()) {
            throw new EntityNotFoundException("could not find Owner with name: " + firstName);
        }
        else return owner.get();
    }

    public Owner findById(Long id) {
        var owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new EntityNotFoundException("could not find Owner with id: " + id);
        }
        else return owner.get();
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner findByFirstNameWithPets(String firstName) {
        Optional<Owner> owner = ownerRepository.findByFirstNameWithPets(firstName);
        if (owner.isEmpty()) {
            throw new EntityNotFoundException("could not find Owner with first name: " + firstName);
        }
        else return owner.get();
    }

    public void delete(Owner owner) {
        this.ownerRepository.delete(owner);
    }
}
