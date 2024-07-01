package com.petclinic.core

import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class OwnerService(
    val ownerRepository: OwnerRepository) {

    fun findByFirstName(name: String): Owner {
        return ownerRepository.findByFirstName(name) ?: throw EntityNotFoundException("Owner with first name $name not found")
    }

    fun findById(id: Int) : Owner {
        return ownerRepository.findById(id).orElseThrow { EntityNotFoundException("Owner with id $id not found") }
    }

    fun save(owner: Owner): Owner {
        return ownerRepository.save(owner)
    }

    @Transactional
    fun transferFunds(ownerToCredit: Owner, ownerToDebit: Owner, amount: BigDecimal) {
        val statementAfterDebit = ownerToDebit.accountStatement - amount
        ownerToDebit.accountStatement = statementAfterDebit

        val statementAfterCredit = ownerToCredit.accountStatement + amount
        ownerToCredit.accountStatement = statementAfterCredit

        ownerRepository.save(ownerToDebit)
        ownerRepository.save(ownerToCredit)
    }

}