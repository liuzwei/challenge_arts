package com.challenge.arts.week33.factory;


import com.challenge.arts.week33.exception.BeansException;

public interface BeanFactory {
    /**
     * 获取Bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName) throws BeansException;
}
