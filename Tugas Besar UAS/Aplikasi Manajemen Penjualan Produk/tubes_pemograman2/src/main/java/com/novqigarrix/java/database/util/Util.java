package com.novqigarrix.java.database.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Util {

    public static String generateRandomString() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    public static String generateRandomString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

}
