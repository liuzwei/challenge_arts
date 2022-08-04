package com.challenge.arts.week32.staticproxy;

/**
 * 计算器实现
 */
public class CalculatorImpl implements Calculator{
    @Override
    public int add(int a, int b) {
        int c = a + b;
        System.out.printf("add %d + %d = %d \n", a, b, c);
        return c;
    }

    @Override
    public int multi(int a, int b) {
        int c = a * b;
        System.out.printf("multi %d * %d = %d \n", a, b, c);
        return c;
    }
}
