package com.petclinic.core

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.util.List

@WebMvcTest(OwnerController::class)
internal class OwnerControllerTest {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockitoBean
    private val ownerService: OwnerService? = null

    @Autowired
    private val objectMapper: ObjectMapper? = null

    @Test
    @Throws(Exception::class)
    fun shouldFindOwnerById() {
        var pets = List.of(Pet(1L, "dog", "luna"))
        val owner = Owner(1L, "joe", "satriani", BigDecimal(1000), pets)
        BDDMockito.given(ownerService!!.findById(1L)).willReturn(owner)

        mockMvc!!.perform(MockMvcRequestBuilders.get("/owner/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("joe"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("satriani"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.accountStatement").value(1000))
            .andExpect(MockMvcResultMatchers.jsonPath("$.pets[0].name").value("luna"))
    }

    @Test
    @Throws(Exception::class)
    fun shouldFindOwnerByFirstName() {
        var pets = List.of(Pet(1L, "dog", "luna"))
        val owner = Owner(1L, "joe", "satriani", BigDecimal(1000), pets)
        BDDMockito.given(ownerService!!.findByFirstName("joe")).willReturn(owner)

        mockMvc!!.perform(MockMvcRequestBuilders.get("/owner/search").param("firstName", "joe"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("joe"))
    }
}
