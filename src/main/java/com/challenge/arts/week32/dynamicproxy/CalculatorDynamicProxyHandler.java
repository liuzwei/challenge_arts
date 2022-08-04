package com.challenge.arts.week32.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CalculatorDynamicProxyHandler implements InvocationHandler {

    private final Object target;

    public CalculatorDynamicProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.printf("invoke method = %s \n", method.getName());
        Object result = method.invoke(target, args);
        return result;
    }
}
