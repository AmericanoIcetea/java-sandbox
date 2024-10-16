package com.americanoicetea.java.bean.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.americanoicetea.java.bean.repository.BeanSessionRepo;

import jakarta.servlet.http.HttpSession;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class BeanSessionService {

    private AtomicInteger count = new AtomicInteger(0);

    public BeanSessionService(HttpSession httpSession, BeanSessionRepo repo){
        repo.addSessionBean(httpSession.getId(), this);
    }

    public Integer getIncrement(){
        return count.addAndGet(1);
    }

    public Integer get(){
        return count.get();
    }
}
