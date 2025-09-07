package com.simple.boat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.boat.model.Boat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BoatIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldLoginAndCreateBoat() throws Exception {
        String token = loginAndGetToken("user", "password");

        Boat boat = new Boat("Titanic", "Big ship");

        mockMvc.perform(post("/api/boats")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boat)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Titanic"));
    }

    @Test
    void findAllShouldReturnUnauthorizedWhenNoToken() throws Exception {
        mockMvc.perform(get("/api/boats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden()); // or .isUnauthorized() depending on config
    }

    private String loginAndGetToken(String username, String password) throws Exception {
        String body = objectMapper.writeValueAsString(Map.of("username", username, "password", password));
        String response = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(response).get("token").asText();
    }
}
