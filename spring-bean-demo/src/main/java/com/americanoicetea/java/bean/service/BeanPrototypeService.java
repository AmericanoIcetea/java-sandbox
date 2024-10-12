package com.americanoicetea.java.bean.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class BeanPrototypeService {

    private AtomicInteger count = new AtomicInteger(0);

    public Integer getIncrement(){
        return count.addAndGet(1);
    }

    public Integer get(){
        return count.get();
    }
}
