package com.jaravir.personsapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreatePerson() throws Exception {
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk()).andExpect(content().json("{'persons': []}"));
        this.mockMvc.perform(
                post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"birthDate\": \"2000-12-04\", \"currentAddress\": \"Rotterdam, somestreet 33\"}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(content().json("{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"birthDate\": \"2000-12-04\", \"currentAddress\": \"Rotterdam, somestreet 33\"}"));

        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk()).andExpect(content().json("{'persons': [{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"birthDate\": \"2000-12-04\", \"currentAddress\": \"Rotterdam, somestreet 33\"}]}"));
    }

    @Test
    void testCreateDuplicate() throws Exception {
        this.mockMvc.perform(
                post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"John\", \"lastName\": \"Doe\", \"birthDate\": \"2000-12-04\", \"currentAddress\": \"Rotterdam, somestreet 33\"}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict()).andExpect(content().string("Person with firstName John and lastName Doe already exists."));
    }

    @Test
    void testUpdateAddress() throws Exception {
        this.mockMvc.perform(
                put("/persons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"currentAddress\": \"Amsterdam, someotherstreet 918\"}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk()).andExpect(content().json("{'persons': [{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"birthDate\": \"2000-12-04\", \"currentAddress\": \"Amsterdam, someotherstreet 918\"}]}"));
    }
}
