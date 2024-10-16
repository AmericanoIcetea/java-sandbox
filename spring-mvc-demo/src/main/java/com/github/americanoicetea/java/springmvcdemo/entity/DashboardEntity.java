package com.github.americanoicetea.java.springmvcdemo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DashboardEntity {

    @Id
    private LocalDate eventDate;
    private BigDecimal totalCost;

    public DashboardEntity(LocalDate eventDate, BigDecimal totalCost){
        this.eventDate = eventDate;
        this.totalCost = totalCost;
    }
    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
