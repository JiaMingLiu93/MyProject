package com.liu.localDate;

import org.joda.time.Days;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
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
    public static Integer getIntervalDays(Date dateFrom, Date dateTo){
        //将转换的两个时间对象转换成Calendard对象
        Calendar can1 = Calendar.getInstance();
        can1.setTime(dateFrom);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(dateTo);
        //拿出两个年份
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);
        //天数
        int days = 0;
        Calendar can = null;
        //减去小的时间在这一年已经过了的天数
        //加上大的时间已过的天数
        days -= can1.get(Calendar.DAY_OF_YEAR);
        days += can2.get(Calendar.DAY_OF_YEAR);
        can = can1;
        for (int i = 0; i < Math.abs(year2-year1); i++) {
            //获取小的时间当前年的总天数
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            //再计算下一年。
            can.add(Calendar.YEAR, 1);
        }
        return days+1;
    }
    public static int daysBetween(Date dateFrom,Date dateTo){
        org.joda.time.LocalDate localDate = org.joda.time.LocalDate.fromDateFields(dateFrom);
        org.joda.time.LocalDate localDate1 = org.joda.time.LocalDate.fromDateFields(dateTo);
        return Days.daysBetween(localDate1, localDate).getDays();
    }
}
