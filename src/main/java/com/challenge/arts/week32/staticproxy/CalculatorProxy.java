package com.challenge.arts.week32.staticproxy;

public class CalculatorProxy  implements Calculator{

    private final CalculatorImpl calculator;

    public CalculatorProxy(CalculatorImpl calculator) {
        this.calculator = calculator;
    }

    @Override
    public int add(int a, int b) {
        System.out.println("execute add method");
        return calculator.add(a, b);
    }

    @Override
    public int multi(int a, int b) {
        System.out.println("execute multi method");
        return calculator.multi(a, b);
    }
}
