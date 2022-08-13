package com.challenge.arts.week33.factory.support;


import com.challenge.arts.week33.exception.BeansException;
import com.challenge.arts.week33.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition definition) throws BeansException {
        Object beanObj = null;
        try {
            beanObj = definition.getBeanClass().newInstance();
        }catch (Exception e) {
            throw new BeansException("Instantiation bean exception", e);
        }
        addSingletonObject(beanName, beanObj);
        return beanObj;
    }
}
