package com.petclinic.core;

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
    @Autowired
    private VisitService visitService;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        var visit = new Visit(null, "V01-23", LocalDate.of(2013, 12, 21), "Teeth whitening");
        var pet = new Pet(null, "dog", "Luna");
        visit.setPet(pet);
        visit.setOwner(new Owner(null, "joe", "satriani", 1000));
        this.visitService.save(visit);
    }

    @Test
    public void shouldFindVisit() {
        assertThat(this.visitService.findByReferenceNumber("V01-23").getReferenceNumber()).isEqualTo("V01-23");
    }

    @Test
    public void shouldNotFindVisit() {
        assertThat(this.visitService.findByReferenceNumber("bla")).isEqualTo(null);
    }

    @Test
    public void shouldDeleteVisit() {
        var visit = this.visitService.findByReferenceNumber("V01-23");
        this.visitService.delete(visit);
        assertThat(this.visitService.findByReferenceNumber("V01-23")).isEqualTo(null);
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
