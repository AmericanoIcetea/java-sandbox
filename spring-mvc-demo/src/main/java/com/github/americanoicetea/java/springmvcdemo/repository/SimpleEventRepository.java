package com.github.americanoicetea.java.springmvcdemo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.americanoicetea.java.springmvcdemo.entity.EventEntity;

public interface SimpleEventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findTop3ByOrderByEventCostDesc();

    List<EventEntity> findTop3ByOrderByEventDateDesc();

    @Query("SELECT SUM(eventCost) FROM EventEntity")
    BigDecimal sumByCost();

    @Query("SELECT e.eventDate, SUM(e.eventCost) FROM EventEntity e GROUP BY e.eventDate")
    List<Object[]> groupByEventDateAndSumEventCost();
}
