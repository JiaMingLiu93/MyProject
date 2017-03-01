package com.liu.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Jam on 2017/2/27.
 */
@Component
public class TimerTaskSchedule {
    private static int count = 1;

    @Scheduled(cron = "0/30 * * * * ?")
    public void runTimerTask() {
        System.out.println("i am in a timer task.the times is "+(count++)+"==="+LocalDateTime.now());
    }
}
