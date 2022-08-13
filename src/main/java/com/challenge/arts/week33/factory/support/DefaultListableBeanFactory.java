package com.challenge.arts.week33.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.challenge.arts.week33.exception.BeansException;
import com.challenge.arts.week33.factory.config.BeanDefinition;


public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    /**
     * save BeanDefinition
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String name) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new BeansException("No bean named " + name +" is defined");
        }
        return beanDefinition;
    }
}
