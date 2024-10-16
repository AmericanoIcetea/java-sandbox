package com.github.americanoicetea.java.springmvcdemo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.americanoicetea.java.springmvcdemo.domain.Event;
import com.github.americanoicetea.java.springmvcdemo.entity.DashboardEntity;
import com.github.americanoicetea.java.springmvcdemo.service.SimpleEventService;



@RestController
public class SimpleApiController {

    @Autowired
    private SimpleEventService service;

    @GetMapping("/simple/event/{id}")
    public ResponseEntity<Event> getMethodName(@PathVariable Long id) {
        var event = service.getEventId(id);
        if(event == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/simple/event/top3")
    public ResponseEntity<List<Event>> getTop3(@RequestParam String by) {
        var events = service.getTop3EventsBy(by);
        if(events == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/simple/event/total")
    public ResponseEntity<BigDecimal> getSumCost(@RequestParam String by) {
        var sum = service.getSumBy(by);
        if(sum == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    @GetMapping("/simple/scan/eventDashboard")
    public ResponseEntity<List<DashboardEntity>> getEventDashboard() {
        var sum = service.getEventDashboard();
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

}