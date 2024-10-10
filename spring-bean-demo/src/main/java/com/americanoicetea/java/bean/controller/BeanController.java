package com.americanoicetea.java.bean.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.americanoicetea.java.bean.service.BeanSessionService;
import com.americanoicetea.java.bean.service.BeanSingletonService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/bean")
public class BeanController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BeanSessionService beanSessionService;

    @Autowired
    private BeanSingletonService beanSingletonService;

    @PostMapping("/session/increment")
    public Integer incrementPerSesion(HttpSession httpSession) {
        return beanSessionService.getIncrement();
    }

    @PostMapping("/singleton/increment")
    public Integer incrementSingleton(HttpSession httpSession) {
        return beanSingletonService.getIncrement();
    }

    @GetMapping("/names")
    public String[] getBeanNames() {
        return applicationContext.getBeanNamesForType(BeanSessionService.class);
    }

    @GetMapping("/all")
    public Map<String, Integer> getIncrement() {
        var result = new HashMap<String, Integer>();
        var sessionBean = applicationContext.getBean("beanSessionService", BeanSessionService.class);
        var singletonBean = applicationContext.getBean("beanSingletonService", BeanSingletonService.class);
        result.put(singletonBean.getClass().getName(), singletonBean.get());
        result.put(sessionBean.getClass().getName(), sessionBean.get());
        return result;
    }

}
