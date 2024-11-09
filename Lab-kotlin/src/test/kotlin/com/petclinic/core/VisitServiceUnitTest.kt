package com.petclinic.core

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDate
import java.util.*

internal class VisitServiceUnitTest {
    private val visitRepository = Mockito.mock(VisitRepository::class.java)
    private val visitService = VisitService(visitRepository)

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun shouldFindVisitByReferenceNumber() {
        val visit = Visit(1, LocalDate.now(), "teeth whitening", null, null)
        Mockito.`when`(visitRepository.findById(1)).thenReturn(Optional.of(visit))

        val foundVisit = visitService.findById(1)

        assertThat(foundVisit?.id).isEqualTo(1)
    }
}
