package com.petclinic.core

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.time.LocalDate
import java.util.List

@WebMvcTest(VisitController::class)
internal class VisitControllerTest {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockitoBean
    private val visitService: VisitService? = null

    @Test
    @Throws(Exception::class)
    fun shouldFindVisitById() {
        val pet = Pet(1L, "dog", "luna")
        val owner = Owner(1L, "joe", "satriani", BigDecimal(1000), List.of(pet))

        val visit = Visit(1L, LocalDate.parse("2013-12-21"), "Teeth whitening",  owner, pet)
        visit.pet = pet
        visit.owner = owner
        BDDMockito.given(visitService!!.findById(1L)).willReturn(visit)

        mockMvc!!.perform(MockMvcRequestBuilders.get("/visit/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.pet.type").value("dog"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.pet.name").value("luna"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.owner.firstName").value("joe"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.owner.lastName").value("satriani"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.owner.accountStatement").value(1000))
            .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2013-12-21"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.purpose").value("Teeth whitening"))
    }
}
