package com.americanoicetea.java.bean.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.americanoicetea.java.bean.repository.BeanSessionRepo;
import com.americanoicetea.java.bean.service.BeanPrototypeService;
import com.americanoicetea.java.bean.service.BeanSessionService;
import com.americanoicetea.java.bean.service.BeanSingletonService;

@RestController
@RequestMapping("/bean")
public class BeanController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BeanSessionService beanSessionService;

    @Autowired
    private BeanSingletonService beanSingletonService;

    @Autowired
    private BeanPrototypeService beanPrototypeService;

    @Autowired
    private BeanSessionRepo beanSessionRepo;

    @PostMapping("/session/increment")
    public Integer incrementPerSesion() {
        return beanSessionService.getIncrement();
    }

    @PostMapping("/singleton/increment")
    public Integer incrementSingleton() {
        return beanSingletonService.getIncrement();
    }

    @PostMapping("/prototype/increment")
    public Integer incrementPrototype() {
        return beanPrototypeService.getIncrement();
    }

    @GetMapping("/all")
    public Map<String, Integer> getIncrement() {
        var result = new HashMap<String, Integer>();
        var singletonBean = applicationContext.getBean("beanSingletonService", BeanSingletonService.class);
        var prototypeBean = applicationContext.getBean("beanPrototypeService", BeanPrototypeService.class);
        var sessionBeans = beanSessionRepo.getAllBeanServices();
        sessionBeans.forEach(entry -> {
            result.put(entry.getKey(), entry.getValue().get());
        });
        result.put(singletonBean.getClass().getName(), singletonBean.get());
        result.put(prototypeBean.getClass().getName(), prototypeBean.get());
        return result;
    }

}
