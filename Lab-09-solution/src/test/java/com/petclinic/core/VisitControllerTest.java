package com.petclinic.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VisitController.class)
class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VisitService visitService;

    @Test
    void shouldFindVisitById() throws Exception {
        Pet pet = new Pet(1L, "dog", "luna");
        Owner owner = new Owner(1L, "joe", "satriani", 1000);
        owner.setPets(List.of(pet));

        Visit visit = new Visit(1L, "V01-23", LocalDate.parse("2013-12-21"), "Teeth whitening");
        visit.setPet(pet);
        visit.setOwner(owner);
        given(visitService.findById(1L)).willReturn(visit);

        mockMvc.perform(get("/visit/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pet.type").value("dog"))
                .andExpect(jsonPath("$.pet.name").value("luna"))
                .andExpect(jsonPath("$.owner.firstName").value("joe"))
                .andExpect(jsonPath("$.owner.lastName").value("satriani"))
                .andExpect(jsonPath("$.owner.accountStatement").value(1000))
                .andExpect(jsonPath("$.referenceNumber").value("V01-23"))
                .andExpect(jsonPath("$.date").value("2013-12-21"))
                .andExpect(jsonPath("$.purpose").value("Teeth whitening"));
    }
}
