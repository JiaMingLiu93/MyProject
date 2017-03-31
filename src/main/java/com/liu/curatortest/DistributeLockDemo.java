package com.liu.curatortest;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jam on 2017/3/15.
 */
public class DistributeLockDemo {
    /** Zookeeper info */
    private static final String ZK_ADDRESS = "192.168.107.110:2181";
    private static final String ZK_LOCK_PATH = "/zktest";

    public static void main(String[] args) throws InterruptedException {
        // 1.Connect to zk

        System.out.println("zk client start successfully!");

        Thread t1 = new Thread(() -> {
            CuratorFramework client = CuratorFrameworkFactory.newClient(
                    ZK_ADDRESS,
                    new RetryNTimes(10, 5000)
            );
            client.start();

            CountDownLatch latch = new CountDownLatch(5);
            int count[] = new int[1];
            count[0] = 0;
            InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    doWithLock(lock, latch, count);
                    if (count[0]>10){
                        client.close();
                        throw new RuntimeException();
                    }
                }
            };

            Timer timer = new Timer();
            timer.schedule(timerTask, Calendar.getInstance().getTime(),5000);

        }, "t1");


        Thread t2 = new Thread(() -> {

            CuratorFramework client = CuratorFrameworkFactory.newClient(
                    ZK_ADDRESS,
                    new RetryNTimes(10, 5000)
            );
            client.start();

            InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);

            CountDownLatch latch = new CountDownLatch(5);
            int count[] = new int[1];
            count[0] = 0;
            TimerTask timerTask1 = new TimerTask() {
                @Override
                public void run() {
                    doWithLock(lock,latch,count);
                    if (count[0]>10){
                        client.close();
                        throw new RuntimeException();
                    }
                }
            };


            Timer timer1 = new Timer();
            timer1.schedule(timerTask1,Calendar.getInstance().getTime(),5000);
            //try {
            //    latch.await();
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            //timer1.cancel();
        }, "t2");


        t1.start();
        t2.start();
    }

    private static void doWithLock(InterProcessMutex lock, CountDownLatch latch, int[] count) {

        System.out.println(Thread.currentThread().getName()+"is calling doWithLock.");
            if (lock.isAcquiredInThisProcess()){
                latch.countDown();
                count[0]++;
                System.out.println(Thread.currentThread().getName()+"is still hold the lock.");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"done the job.");
            }else {
                try {
                    if (lock.acquire(2, TimeUnit.SECONDS)){
                        latch.countDown();
                        count[0]++;
                        System.out.println(Thread.currentThread().getName() + " hold lock");
                        try {
                            Thread.sleep(3000L);
                            System.out.println(Thread.currentThread().getName()+"done the job.");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println(Thread.currentThread().getName()+"can not hold lock.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        //try {
        //    lock.acquire();
        //    System.out.println(Thread.currentThread().getName() + " hold lock");
        //    Thread.sleep(5000L);
        //    System.out.println(Thread.currentThread().getName() + " release lock");
        //    //if (lock.acquire()) {
        //    //    System.out.println(Thread.currentThread().getName() + " hold lock");
        //    //    Thread.sleep(5000L);
        //    //    System.out.println(Thread.currentThread().getName() + " release lock");
        //    //}
        //} catch (Exception e) {
        //    e.printStackTrace();
        //} finally {
        //    try {
        //        lock.release();
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //}

    }
}
