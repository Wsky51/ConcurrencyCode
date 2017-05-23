package com.wuyi.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by LENOVO on 2017/5/23.
 */
public class CountDownLatchDemo {
    private static CountDownLatch countDownLatch=new CountDownLatch(3);
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A已经到达目的地");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B已经到达目的地");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("C已经到达目的地");
                countDownLatch.countDown();
            }
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("三人已经都到达，任务完成");
    }
}
