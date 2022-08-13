package com.challenge.arts.week33.factory.support;

import com.challenge.arts.week33.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
