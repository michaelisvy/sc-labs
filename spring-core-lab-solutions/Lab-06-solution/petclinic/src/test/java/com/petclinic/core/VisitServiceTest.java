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

    @BeforeEach
    public void setup() {
        var visit = new Visit(0, "V01-23", LocalDate.of(2013, 12, 21), "Teeth whitening");
        this.visitService.save(visit);
    }

    @Test
    public void shouldFindVisit() {
        assertThat(this.visitService.findByReferenceNumber("V01-23").getReferenceNumber()).isEqualTo("V01-23");
    }

    @Test
    public void shouldNotFindVisitWithReferenceNumber() {
        assertThat(this.visitService.findByReferenceNumber("bla")).isNull();
    }
}
