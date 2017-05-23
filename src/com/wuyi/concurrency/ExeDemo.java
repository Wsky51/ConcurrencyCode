package com.wuyi.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by LENOVO on 2017/5/23.
 */
public class ExeDemo {
    public static void main(String[] args) {
        ExecutorService es= Executors.newCachedThreadPool();
        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1开始执行");
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2开始执行");
            }
        });
        es.shutdown();
        System.out.println(es.isTerminated());
        System.out.println(es.isShutdown());
    }
}
