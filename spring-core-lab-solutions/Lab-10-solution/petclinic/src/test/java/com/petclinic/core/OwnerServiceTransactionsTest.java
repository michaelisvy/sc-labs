package com.petclinic.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OwnerServiceTransactionsTest {

    @Autowired
    private OwnerService ownerService;

    @Test @Transactional
    public void shouldRunInTransaction() {
        assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
    }

    @Test @Transactional
    public void shouldTransferFunds() {
        var ownerToCredit = ownerService.save(new Owner(0, "Jimi","Hendrix", 0));
        var ownerToDebit = ownerService.save(new Owner(0, "Robert","Plant",1000));
        ownerService.transferFunds(ownerToCredit, ownerToDebit, 200);
        var ownerToCreditRetrieved = ownerService.findByFirstName("Jimi");
        assertThat(ownerToCreditRetrieved.getAccountStatement()).isEqualTo(200);
        var ownerToDebitRetrieved = ownerService.findByFirstName("Robert");
        assertThat(ownerToDebitRetrieved.getAccountStatement()).isEqualTo(800);
    }

    @Test @Transactional
    public void shouldFailTransferBecauseAmountBelowZero() {
        var ownerToCredit = ownerService.save(new Owner(0, "Jimi","Hendrix", 0));
        var ownerToDebit = ownerService.save(new Owner(0, "Robert","Plant",1000));
        assertThatThrownBy(() -> {
            ownerService.transferFunds(ownerToCredit, ownerToDebit, 11200);
        }).isInstanceOf(RuntimeException.class);
    }
}
