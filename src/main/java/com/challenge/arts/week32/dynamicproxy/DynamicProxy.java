package com.challenge.arts.week32.dynamicproxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.challenge.arts.week32.staticproxy.Calculator;
import com.challenge.arts.week32.staticproxy.CalculatorImpl;

public class DynamicProxy {

    public static void main(String[] args) throws Throwable {
        CalculatorImpl calculator = new CalculatorImpl();
        Calculator calculatorProxy = (Calculator) Proxy.newProxyInstance(
            calculator.getClass().getClassLoader(),
            calculator.getClass().getInterfaces(),
            new CalculatorDynamicProxyHandler(calculator));
        try {
            calculatorProxy.add(1,3);
        }catch (Exception e) {
            System.out.println("exception:"+ e.getMessage());
        }
        calculatorProxy.multi(10,30);
    }

    public static Object getProxy(Object target) throws Throwable{
        // 获取接口Class对象
//        Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(),target.getClass().getInterfaces());
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().equals(Deprecated.class)){
                        throw new IllegalAccessException("This method has deprecated");
                    }
                }
                System.out.printf("invoke method = %s \n", method.getName());
                Object result = method.invoke(target, args);
                return result;
            }
        });
    }
}
