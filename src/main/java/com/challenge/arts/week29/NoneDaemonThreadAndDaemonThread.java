package com.challenge.arts.week29;

import java.util.stream.IntStream;

/**
 * 守护线程和非守护线程的区别，
 *
 * 非守护线程会一致执行到最后，
 * 守护线程则不会执行到最后，
 */
public class NoneDaemonThreadAndDaemonThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting the execution in the Thread " +      Thread.currentThread().getName());

        Thread daemonThread = new Thread(() ->      IntStream.rangeClosed(1, 100000)
                .forEach(System.out::println));

        daemonThread.setDaemon(true);
        daemonThread.start();

        Thread.sleep(10);

        System.out.println("End of the execution in the Thread " +
                Thread.currentThread().getName());

    }
}
