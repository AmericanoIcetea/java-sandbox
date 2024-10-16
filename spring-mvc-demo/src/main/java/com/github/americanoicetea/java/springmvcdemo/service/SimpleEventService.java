package com.github.americanoicetea.java.springmvcdemo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.americanoicetea.java.springmvcdemo.domain.Event;
import com.github.americanoicetea.java.springmvcdemo.entity.DashboardEntity;
import com.github.americanoicetea.java.springmvcdemo.entity.EventEntity;
import com.github.americanoicetea.java.springmvcdemo.repository.SimpleEventRepository;

import jakarta.annotation.PostConstruct;

@Service
public class SimpleEventService {

    private final SimpleEventRepository repository;

    @PostConstruct
	public void populateEvent() {
        var entities = new ArrayList<EventEntity>();
        var entity = new EventEntity();
        entity.setEventId(1L);
        entity.setEventName("event-1");
        entity.setEventDate(LocalDate.now());
        entity.setEventCost(new BigDecimal(100));
        var entity2 = new EventEntity();
        entity2.setEventId(2L);
        entity2.setEventName("event-2");
        entity2.setEventDate(LocalDate.now().plusDays(1));
        entity2.setEventCost(new BigDecimal(200));
        var entity3 = new EventEntity();
        entity3.setEventId(3L);
        entity3.setEventName("event-3");
        entity3.setEventDate(LocalDate.now().plusDays(2));
        entity3.setEventCost(new BigDecimal(300));
        var entity4 = new EventEntity();
        entity4.setEventId(4L);
        entity4.setEventName("event-4");
        entity4.setEventDate(LocalDate.now().plusDays(3));
        entity4.setEventCost(new BigDecimal(400));
        var entity5 = new EventEntity();
        entity5.setEventId(5L);
        entity5.setEventName("event-5");
        entity5.setEventDate(LocalDate.now().plusDays(3));
        entity5.setEventCost(new BigDecimal(400));

        entities.add(entity);
        entities.add(entity2);
        entities.add(entity3);
        entities.add(entity4);
        entities.add(entity5);
        repository.saveAll(entities);
	}

    public SimpleEventService(SimpleEventRepository repository){
        this.repository = repository;
    }

    public Event getEventId(Long id) {
        var entity = repository.findById(id);
        if(entity.isEmpty()){
            return null;
        }

        return new Event(entity.get());
    }

    public List<Event> getTop3EventsBy(String by){

        if (by.equalsIgnoreCase("eventCost")) {
            return repository.findTop3ByOrderByEventCostDesc()
            .stream().map(Event::new).toList();
        }

        if (by.equalsIgnoreCase("eventDate")) {
            return repository.findTop3ByOrderByEventDateDesc()
            .stream().map(Event::new).toList();
        }

        return null;
    }

    public BigDecimal getSumBy(String by){

        if (by.equalsIgnoreCase("eventCost")) {
            return repository.sumByCost();
        }

        return null;
    }

    public List<DashboardEntity> getEventDashboard(){
        return repository.groupByEventDateAndSumEventCost().stream()
        .map(eachObj -> {
            return new DashboardEntity((LocalDate)eachObj[0], (BigDecimal)eachObj[1]);
        }).toList();
    }
}
