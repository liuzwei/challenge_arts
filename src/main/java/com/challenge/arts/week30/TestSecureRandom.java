package com.challenge.arts.week30;

import java.security.SecureRandom;
import java.util.Random;

public class TestSecureRandom {

    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt(10);

        SecureRandom secureRandom = new SecureRandom();
        int si = secureRandom.nextInt(10);

        System.out.printf("i is %d, and si is %d \n", i, si);
    }
}
