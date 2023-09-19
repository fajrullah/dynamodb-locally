package com.example.controller;

import com.example.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Mock
    private StudentRepository studentRepository;

    @Inject
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDataOfStudents() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReturnDataOfStudent() throws Exception {
        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusCreated() throws Exception {
        mockMvc.perform( post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "  \"name\": \"John\"," +
                                "  \"classNumber\": \"A002341\"," +
                                "  \"classIds\": null" +
                                "}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnBadRequestWhenCreate() throws Exception {
        mockMvc.perform( post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "  \"name\": \"John\"," +
                                "}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void shouldReturnStatusSuccessWhenDelete() throws Exception{
        mockMvc.perform(delete("/api/students/12234"))
                .andExpect(status().isOk())
                .andExpect(content().string("Item not found for id: 12234"));
    }

    @Test
    public void shouldReturnStatusSuccessWhenUpdate() throws Exception{
        mockMvc.perform( put("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "  \"id\": \"1222342\"," +
                                "  \"name\": \"John\"," +
                                "  \"classNumber\": \"A002341\"," +
                                "  \"classIds\": null" +
                                "}"))
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusFailWhenUpdate() throws Exception{
        mockMvc.perform( put("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "  \"name\": \"John\"," +
                                "  \"classNumber\": \"A002341\"" +
                                "}"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Student ID is required"));
    }

}
