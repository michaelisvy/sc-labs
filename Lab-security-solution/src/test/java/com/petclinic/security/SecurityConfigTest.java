package com.petclinic.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void userEndpointShouldRequireAuthentication() throws Exception {
        mockMvc.perform(get("/user/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void userEndpointShouldBeAccessibleWithUserRole() throws Exception {
        mockMvc.perform(get("/user/")
                        .with(httpBasic("user1", "password")))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void userEndpointShouldBeAccessibleWithAdminRole() throws Exception {
        mockMvc.perform(get("/user/"))
                .andExpect(status().isOk());
    }
}
