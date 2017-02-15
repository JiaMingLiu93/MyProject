package com.liu.localDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Created by jam on 2017/2/9.
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(1486533054L), TimeZone
                .getDefault().toZoneId());
        System.out.println(localDateTime.toString());
    }
}
