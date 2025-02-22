package com.petclinic.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerController.class)
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OwnerService ownerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindOwnerById() throws Exception {
        Owner owner = new Owner(1L, "joe", "satriani", 1000);
        owner.setPets(List.of(new Pet(1L, "dog", "luna")));
        given(ownerService.findById(1L)).willReturn(owner);

        mockMvc.perform(get("/owner/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("joe"))
                .andExpect(jsonPath("$.lastName").value("satriani"))
                .andExpect(jsonPath("$.accountStatement").value(1000))
                .andExpect(jsonPath("$.pets[0].name").value("luna"));
    }

    @Test
    void shouldFindOwnerByFirstName() throws Exception {
        Owner owner = new Owner(1L, "joe", "satriani", 1000);
        given(ownerService.findByFirstName("joe")).willReturn(owner);

        mockMvc.perform(get("/owner/search").param("firstName", "joe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("joe"));
    }

    @Test
    void shouldSaveNewOwner() throws Exception {
        Owner owner = new Owner(null, "joe", "satriani", 1000);
        Owner savedOwner = new Owner(1L, "joe", "satriani", 1000);
        given(ownerService.save(any(Owner.class))).willReturn(savedOwner);

        mockMvc.perform(post("/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(owner)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("joe"));
    }
}
