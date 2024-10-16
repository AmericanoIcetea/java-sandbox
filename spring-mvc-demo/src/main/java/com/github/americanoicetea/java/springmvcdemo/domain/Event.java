package com.github.americanoicetea.java.springmvcdemo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.github.americanoicetea.java.springmvcdemo.entity.EventEntity;

public class Event {

    private Long eventId;

    private String eventName;

    private BigDecimal eventCost;

    private LocalDate eventDate;

    public Event(){

    }

    public Event(EventEntity entity){
        eventId = entity.getEventId();
        eventName = entity.getEventName();
        eventCost = entity.getEventCost();
        eventDate = entity.getEventDate();
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getEventCost() {
        return eventCost;
    }

    public void setEventCost(BigDecimal eventCost) {
        this.eventCost = eventCost;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
}
