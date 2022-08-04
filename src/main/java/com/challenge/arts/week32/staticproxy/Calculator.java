package com.challenge.arts.week32.staticproxy;

/**
 * 计算器接口
 */
public interface Calculator {

    /**
     * 加法
     * @param a
     * @param b
     * @return
     */
    @Deprecated
    int add(int a, int b);

    /**
     * 乘法
     * @param a
     * @param b
     * @return
     */
    int multi(int a, int b);
}
