package com.challenge.arts.week30;

import java.io.Serializable;
import java.util.Optional;

import lombok.Data;

public class TestOptional {

    public static void main(String[] args) {
        System.out.println(request().toString());
    }

    public static Result request(){
        Result result = new Result("equal", "0", "this is equal");
//        Result result = null;

        return Optional.ofNullable(result)
            .map(r -> {
                if ( "0".equals(r.code)) {
                    return new Result("equal", "0", "this is equal");
                }
                return new Result("noequal", "1", "this is not equal");
            }).orElse(new Result("null", "0", "this is null"));
    }

    @Data
    static class Result implements Serializable {
        public Result(String data, String code, String msg) {
            this.data = data;
            this.code = code;
            this.msg = msg;
        }

        String data;
        String code;
        String msg;

        @Override
        public String toString(){
            return "{data:"+data+",code:"+code+", msg:"+msg+"}";
        }
    }
}
