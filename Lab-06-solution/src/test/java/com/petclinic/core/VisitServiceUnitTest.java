package com.petclinic.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitServiceUnitTest {
    @Mock
    private VisitRepository visitRepository;
    @InjectMocks
    private VisitService visitService;

    @Test
    public void shouldFindVisitByReferenceNumber() {
        var visit = new Visit(1L, "V01-23", LocalDate.of(2013, 12, 21), "Teeth whitening");
        when(this.visitRepository.findByReferenceNumber("V01-23")).thenReturn(visit);
        assertThat(this.visitService.findByReferenceNumber("V01-23").getReferenceNumber()).isEqualTo("V01-23");
    }

}
