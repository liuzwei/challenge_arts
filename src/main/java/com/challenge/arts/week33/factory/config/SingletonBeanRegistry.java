package com.challenge.arts.week33.factory.config;

public interface SingletonBeanRegistry {

    /**
     * 根据bean名字获取单例Bean
     * @param beanName
     * @return
     */
    Object getSingletonBean(String beanName);
}
