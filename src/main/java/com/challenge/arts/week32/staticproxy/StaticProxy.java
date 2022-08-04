package com.challenge.arts.week32.staticproxy;

public class StaticProxy {
    public static void main(String[] args) {
        CalculatorProxy calculatorProxy = new CalculatorProxy(new CalculatorImpl());
        calculatorProxy.add(1 , 2);
        calculatorProxy.multi(10, 20);
    }
}
