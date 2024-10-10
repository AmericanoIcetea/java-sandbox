package com.americanoicetea.java.bean.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class BeanSingletonService {

    private AtomicInteger count = new AtomicInteger(0);

    public Integer getIncrement(){
        return count.addAndGet(1);
    }

    public Integer get(){
        return count.get();
    }
}
