package com.petclinic.core

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class VisitServiceUnitTest {
    private val visitRepository = Mockito.mock(VisitRepository::class.java)
    private val visitService = VisitService(visitRepository)

    @Test
    fun shouldFindVisitByReferenceNumber() {
        val visit = Visit(1, LocalDate.now(), "teeth whitening", null, null)
        Mockito.`when`(visitRepository.findById(1)).thenReturn(Optional.of(visit))

        val foundVisit = visitService.findById(1)

        assertThat(foundVisit.id).isEqualTo(1)
    }
}
