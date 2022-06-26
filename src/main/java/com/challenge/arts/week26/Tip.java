package com.challenge.arts.week26;

/**
 * @author liuzhaowei
 * @date 2022/6/26 11:00
 */
public class Tip {
    public static void main(String[] args) {
        String a = "Hello";
        String b = "World";

        System.out.println(a +" "+ b);
        String c = "";
        c = a ;
        a = b;
        b = c;

        System.out.println(a +" "+ b);
    }
}
