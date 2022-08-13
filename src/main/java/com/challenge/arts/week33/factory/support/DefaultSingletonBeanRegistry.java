package com.challenge.arts.week33.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.challenge.arts.week33.factory.config.SingletonBeanRegistry;


public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    @Override
    public Object getSingletonBean(String beanName) {

        return singletonObjects.get(beanName);
    }

    protected void addSingletonObject(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }


}
