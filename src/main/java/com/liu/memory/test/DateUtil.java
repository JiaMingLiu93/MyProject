package com.liu.memory.test;


import java.util.*;

/**
 * Created by admin on 2017/3/30.
 */
public class DateUtil {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date end = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -15);
        Date start = calendar.getTime();
        int i=0;
        while (true){
            System.out.println("========");
            getPartitionedDates(end,end,PeriodType.DAY);
            System.out.println(i++);
        }
    }
    public static boolean sameDay(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int day1 = calendar.get(Calendar.DATE);
        int month1 = calendar.get(Calendar.MONTH);
        int year1 = calendar.get(Calendar.YEAR);
        calendar.setTime(date2);
        int day2 = calendar.get(Calendar.DATE);
        int month2 = calendar.get(Calendar.MONTH);
        int year2 = calendar.get(Calendar.YEAR);
        return year2 == year1 && month1 == month2 && day1 == day2;
    }

    public static List<Date> getPartitionedDates(Date dateFrom, Date dateTo, PeriodType periodType) {
        PeriodType[] periodTypes = new PeriodType[]{PeriodType.DAY, PeriodType.WEEK, PeriodType.MONTH, PeriodType.YEAR};
        int[] dateAddStepTypes = new int[]{Calendar.DATE, Calendar.DATE, Calendar.MONTH, Calendar.YEAR};
        int[] dateAddStepValues = new int[]{1, 7, 1, 1};
        for (int i = 0; i < periodTypes.length; ++i) {
            PeriodType tmp = periodTypes[i];
            if (tmp == periodType) {
                List<Date> dateList = new ArrayList<>();
                Date nextDate = dateFrom;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(nextDate);
                do {
                    dateList.add(nextDate);
                    calendar.add(dateAddStepTypes[i], dateAddStepValues[i]);
                    nextDate = calendar.getTime();
                } while (!sameDay(nextDate, dateTo));
                dateList.add(nextDate);
                return dateList;
            }
        }
        return Collections.emptyList();
    }
}
