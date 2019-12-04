package com.exam.pisti.utils;

import java.util.concurrent.atomic.AtomicLong;

public class IdentityNoGenerator {

    /**
     * Thread safe Id generation,
     * AtomicLong chosen for thread safe concurrency purpose,
     * */
    private static AtomicLong idCounter = new AtomicLong();

    public static String getBotID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }

}
