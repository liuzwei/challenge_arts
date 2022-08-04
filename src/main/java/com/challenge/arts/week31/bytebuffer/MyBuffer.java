package com.challenge.arts.week31.bytebuffer;

import java.nio.ByteBuffer;

public class MyBuffer {
    public static void main(String[] args) {
        String s = "abcdefghijk";
        // 声明一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println("---------------allocate----------------");
        System.out.printf("capacity= %d \n", byteBuffer.capacity());
        System.out.printf("position= %d \n", byteBuffer.position());
        System.out.printf("limit= %d \n", byteBuffer.limit());

        byteBuffer.put(s.getBytes());

        System.out.println("---------------put----------------");
        System.out.printf("capacity= %d \n", byteBuffer.capacity());
        System.out.printf("position= %d \n", byteBuffer.position());
        System.out.printf("limit= %d \n", byteBuffer.limit());

    }
}
