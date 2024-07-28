package com.petclinic.core;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest @Transactional
public class VisitServiceTest {
    private final VisitService visitService;

    private final ApplicationContext applicationContext;

    @Autowired
    public VisitServiceTest(VisitService visitService, ApplicationContext applicationContext) {
        this.visitService = visitService;
        this.applicationContext = applicationContext;
    }

    @BeforeEach
    public void setup() {
        var visit = new Visit(0, "V01-23", LocalDate.of(2013, 12, 21), "Teeth whitening");
        var pet = new Pet(0, "dog", "Luna");
        visit.setPet(pet);
        visit.setOwner(new Owner(0, "joe", "satriani", 1000));
        this.visitService.save(visit);
    }

    @Test
    public void shouldFindVisit() {
        assertThat(this.visitService.findByReferenceNumber("V01-23").getReferenceNumber()).isEqualTo("V01-23");
    }

    @Test
    public void shouldNotFindVisit() {
        assertThatThrownBy( () -> {
            this.visitService.findByReferenceNumber("bla");
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void shouldDeleteVisit() {
        var visit = this.visitService.findByReferenceNumber("V01-23");
        this.visitService.delete(visit);
        assertThatThrownBy( () -> {
            this.visitService.findByReferenceNumber("V01-23");
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void shouldFindVisitWithPet() {
        assertThat(this.visitService.findByReferenceNumber("V01-23").getPet().getName()).isEqualTo("Luna");
    }

    @Test
    public void shouldFindVisitWithOwner() {
        assertThat(this.visitService.findByReferenceNumber("V01-23").getOwner().getLastName()).isEqualTo("satriani");
    }

    @Test
    public void shouldDisplayBeanNumber() {
        System.out.println(this.applicationContext.getBeanDefinitionCount());
    }
}
