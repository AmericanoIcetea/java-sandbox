package com.americanoicetea.java.bean.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.americanoicetea.java.bean.service.BeanSessionService;

@Repository
public class BeanSessionRepo {

    public Map<String, BeanSessionService> sessionMap = Collections.synchronizedMap(new HashMap<>());

    public BeanSessionService findBySessionId(String sessionId){
        return sessionMap.get(sessionId);
    }

    public void addSessionBean(String sessionId, BeanSessionService beanSessionService){
        sessionMap.put(sessionId, beanSessionService);
    }

    public Set<Map.Entry<String, BeanSessionService>>getAllBeanServices(){
        return sessionMap.entrySet();
    }
}
