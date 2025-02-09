package com.petclinic.core

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager
import java.math.BigDecimal


@SpringBootTest
@Transactional
class OwnerServiceTest(
                @Autowired val ownerService: OwnerService,
                @Autowired val ownerRepository: OwnerRepository) {

    @Test
    fun shouldFindOwner() {
        val owner = ownerService.findById(1)
        assertThat(owner.id).isEqualTo(1)
    }

    @Test
    fun shouldFindByFirstName() {
        val owner = ownerService.findByFirstName("joe")
        assertThat(owner.id).isEqualTo(1)
    }

    @Test
    fun shouldSaveOwner() {
        val pet = Pet(null, "hamster", "frosty")
        val owner = Owner(null, "Robert","Plant", BigDecimal(100), listOf(pet))
        ownerService.save(owner)
        assertThat(pet.id).isNotNull()
        assertThat(owner.id).isNotNull()
    }

    @Test
    fun shouldTransferFunds() {
        assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
        val ownerToCredit = ownerRepository.save(Owner(null, "JimiC","Hendrix", BigDecimal(1), null))
        val ownerToDebit = ownerRepository.save(Owner(null, "RobertC","Plant", BigDecimal(1000), null))
        ownerService.transferFunds(ownerToCredit, ownerToDebit, BigDecimal(200))

        val ownerToCreditRetrieved = ownerService.findByFirstName("JimiC")
        assertThat(ownerToCreditRetrieved.accountStatement).isEqualTo(BigDecimal(201))

        val ownerToDebitRetrieved = ownerService.findByFirstName("RobertC")
        assertThat(ownerToDebitRetrieved.accountStatement).isEqualTo(BigDecimal(800))
    }
}