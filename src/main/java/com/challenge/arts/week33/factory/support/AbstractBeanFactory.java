package com.challenge.arts.week33.factory.support;


import com.challenge.arts.week33.exception.BeansException;
import com.challenge.arts.week33.factory.BeanFactory;
import com.challenge.arts.week33.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singletonBean = getSingletonBean(beanName);
        if (singletonBean != null) {
            return singletonBean;
        }

        // 获取BeanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        // 返回Bean
        return createBean(beanName, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition definition) throws BeansException;
}
