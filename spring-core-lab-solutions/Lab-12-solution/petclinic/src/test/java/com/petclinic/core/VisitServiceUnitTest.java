package com.petclinic.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VisitServiceUnitTest {
    @Mock
    private VisitRepository visitRepository;
    @InjectMocks
    private VisitService visitService;

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    @Test
    public void shouldFindVisitByReferenceNumber() {
        var visit = new Visit(0, "V01-23", LocalDate.of(2013, 12, 21), "Teeth whitening");
        when(this.visitRepository.findByReferenceNumber("V01-23")).thenReturn(Optional.of(visit));
        assertThat(this.visitService.findByReferenceNumber("V01-23").getReferenceNumber()).isEqualTo("V01-23");
    }

}
