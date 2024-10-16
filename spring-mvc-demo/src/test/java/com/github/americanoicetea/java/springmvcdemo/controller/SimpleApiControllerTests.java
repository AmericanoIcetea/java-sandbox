package com.github.americanoicetea.java.springmvcdemo.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.americanoicetea.java.springmvcdemo.domain.Event;
import com.github.americanoicetea.java.springmvcdemo.service.SimpleEventService;

@WebMvcTest(SimpleApiController.class)
public class SimpleApiControllerTests {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private SimpleEventService mockService;

    @Test
    public void givenEventIdShouldReturn200() throws Exception{
        Mockito.when(mockService.getEventId(1L)).thenReturn(new Event());
        mockMvc.perform(MockMvcRequestBuilders.get("/simple/event/1")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void givenNonExistentIdShouldReturn404() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/simple/event/2"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
