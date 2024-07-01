package com.petclinic.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class OwnerService(
    @Autowired val ownerRepository: OwnerRepository) {

    fun findByFirstName(name: String): Owner? {
        return ownerRepository.findByFirstName(name)
    }

    fun findById(id: Int) : Owner {
        return ownerRepository.findById(id).orElseThrow()
    }

    fun save(owner: Owner): Owner {
        return ownerRepository.save(owner)
    }

    @Transactional
    fun transferFunds(ownerToCredit: Owner, ownerToDebit: Owner, amount: BigDecimal) {
        val statementAfterDebit = ownerToDebit.accountStatement - amount
        val newOwnerToDebit = ownerToDebit.copy(accountStatement = statementAfterDebit)

        val statementAfterCredit = ownerToCredit.accountStatement + amount
        val newOwnerToCredit = ownerToCredit.copy(accountStatement = statementAfterCredit)

        ownerRepository.save(newOwnerToCredit)
        ownerRepository.save(newOwnerToDebit)
    }

}