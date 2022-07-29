package com.challenge.arts.week31;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinationMain {

    public static void main(String[] args) {
        // 获取Spring容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 获得一个Bean定义
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(PersonFactoryBean.class);

        //向容器中注册一个bean
        applicationContext.registerBeanDefinition("person", beanDefinition);

        applicationContext.refresh();

        // 获取Person对象
        PersonFactoryBean personFactoryBean = applicationContext.getBean("person", PersonFactoryBean.class);

    }

}
