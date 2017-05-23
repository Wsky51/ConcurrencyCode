package com.wuyi.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by LENOVO on 2017/5/20.
 */
public class TestHarness {
    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException{
        final CountDownLatch startGate=new CountDownLatch(1);
        final CountDownLatch endGate=new CountDownLatch(nThreads);
        for (int i=0;i<nThreads;i++){
            Thread t=new Thread(){
                @Override
                public void run(){
                    try {
                        startGate.await();//使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断。
                        task.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        endGate.countDown();//递减锁存器的计数，如果计数到达零，则释放所有等待的线程。
                    }
                }
            };
            t.start();
        }
        long start=System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end=System.nanoTime();
        return end-start;
    }
}
