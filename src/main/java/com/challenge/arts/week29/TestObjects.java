package com.challenge.arts.week29;

import java.util.Objects;
import java.util.function.Supplier;

public class TestObjects {

    private static String ObjectId = "ABC";
    public static void main(String[] args) {
        // 业务逻辑获取某个对象
        Object someObj = getSomeObject();
        // 验证对象是否为空
        Objects.requireNonNull(someObj, messageSupplier());
//        Objects.requireNonNull(someObj, "某业务对象不能为空");
        // TODO 接下来处理相关逻辑

    }

    private static Object getSomeObject(){

        return null;
    }

    private static Supplier<String> messageSupplier(){
        return () -> {
            return "ObjectId "+ObjectId + "获取对象为空";
        };
    }
}
