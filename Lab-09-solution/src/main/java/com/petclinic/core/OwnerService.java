package com.petclinic.core;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    private static final Logger logger = LoggerFactory.getLogger(OwnerService.class);

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

    public Owner findById(Integer id) {
        Optional<Owner> owner = ownerRepository.findById(id);
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

    @Transactional
    public void transferFunds(Owner ownerToCredit, Owner ownerToDebit, double amount) {
        creditAmount(ownerToCredit, amount);
        debitAmount(ownerToDebit, amount);
    }

    private void debitAmount(Owner ownerToDebit, double amount) {
        double ownerToDebitNewAmount = ownerToDebit.getAccountStatement() - amount;
        if (ownerToDebitNewAmount < 0) {
            logger.error("attempting to have an account with this value: {}. Account value cannot be below zero", ownerToDebitNewAmount);
            throw new RuntimeException("account value cannot be below zero");

        }
        ownerToDebit.setAccountStatement(ownerToDebitNewAmount);
        this.ownerRepository.save(ownerToDebit);
    }

    private void creditAmount(Owner ownerToCredit, double amount) {
        double ownerToCreditNewAmount = ownerToCredit.getAccountStatement() + amount;
        ownerToCredit.setAccountStatement(ownerToCreditNewAmount);
        this.ownerRepository.save(ownerToCredit);
    }
}
