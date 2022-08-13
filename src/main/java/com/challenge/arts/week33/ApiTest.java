package com.challenge.arts.week33;


import com.challenge.arts.week33.factory.config.BeanDefinition;
import com.challenge.arts.week33.factory.support.DefaultListableBeanFactory;

public class ApiTest {

    public static void main(String[] args) {
        // 1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取Bean
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.queryUserInfo();

        UserService userService2 = (UserService) beanFactory.getBean("userService");
        userService2.queryUserInfo();

        System.out.println(userService1 == userService2);
    }

}
