package com.petclinic.core

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
class VisitServiceTest(@Autowired val visitService: VisitService) {

    @Test
    fun shouldFindById() {
        val visit = visitService.findById(1)
        assertThat(visit.id).isEqualTo(1)
        assertThat(visit.pet.id).isEqualTo(1)
        assertThat(visit.owner.id).isEqualTo(1)
    }

    @Test
    fun shouldSaveVisit() {
        val pet = Pet(null, "hamster", "frosty")
        val owner = Owner(null, "Robert","Plant", BigDecimal(100), listOf(pet))
        val visit = Visit(null, LocalDate.now(), "teeth whitening", owner, pet)
        visitService.save(visit)
        assertThat(visit.id).isNotNull()
        assertThat(pet.id).isNotNull()
        assertThat(owner.id).isNotNull()
    }
}