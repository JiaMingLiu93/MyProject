package com.liu.localDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Created by jam on 2017/2/9.
 */
public class LocalDateTest {
    public static void main(String[] args) {
        timestamp2localDateTime();
        localDateTime2timestamp();
    }

    private static void timestamp2localDateTime() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(1486533054L), TimeZone
                .getDefault().toZoneId());
        System.out.println(localDateTime.toString());
    }
    private static void localDateTime2timestamp(){
        LocalDateTime now = LocalDateTime.now();
        long timestamp2 = now.atZone(ZoneOffset.systemDefault()).toEpochSecond();
        long timestamp = now.toEpochSecond(ZoneOffset.UTC);
        System.out.println(timestamp2);
    }
}
