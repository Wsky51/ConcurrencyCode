package com.wuyi.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by LENOVO on 2017/5/23.
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        ExecutorService es= Executors.newCachedThreadPool();
        final Semaphore semp=new Semaphore(5);//只能由5个线程访问
        for (int i=0;i<20;i++){
            final int index=i;
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semp.acquire();
                        System.out.println("Accessing"+index);
                        Thread.sleep(100);
                        semp.release();//访问完成后释放
                        System.out.println("---------------"+index+"已访问完毕"+semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        es.shutdown();

    }
}
