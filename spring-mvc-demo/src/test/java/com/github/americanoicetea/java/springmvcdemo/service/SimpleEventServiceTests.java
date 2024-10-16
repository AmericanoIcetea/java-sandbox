package com.github.americanoicetea.java.springmvcdemo.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.github.americanoicetea.java.springmvcdemo.entity.EventEntity;
import com.github.americanoicetea.java.springmvcdemo.repository.SimpleEventRepository;


@SpringBootTest
public class SimpleEventServiceTests {

    @Autowired
    private SimpleEventService service;

    @MockBean
    private SimpleEventRepository repository;

    @Test
    public void givienExisitingIdThenCallGetEventByIdShouldReturnEventNameCorrectly(){

        var id = 1L;
        var entity = new EventEntity();
        entity.setEventId(id);
        entity.setEventName("event1");
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(new EventEntity()));

        assertEquals(entity.getEventId(), id);
        assertEquals(entity.getEventName(), "event1");
    }

    @Test
    public void givienNonExistentIdThenCallGetEventByIdShouldReturnEventNameCorrectly(){

        var id = 2L;

        var actualResult = service.getEventId(id);

        assertEquals(null, actualResult);
    }

    @TestConfiguration
    static class SimpleEventServiceTestsConfig {

        @Bean
        public SimpleEventService simpleEventService(SimpleEventRepository repository){
            return new SimpleEventService(repository);
        }
    }

}

