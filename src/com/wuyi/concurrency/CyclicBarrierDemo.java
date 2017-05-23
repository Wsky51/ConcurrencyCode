package com.wuyi.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by LENOVO on 2017/5/23.
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        final int count =5;
        final CyclicBarrier barrier=new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("通过栅栏，做其他的事");
            }
        });
        for (int i=0;i<5;i++){
            new Thread(new NewRunner(i,barrier)).start();
        }
    }
}

class NewRunner implements Runnable{
    private final int id;
    private final CyclicBarrier barrier;
    public NewRunner(final int id,final CyclicBarrier barrier){
        this.id=id;
        this.barrier=barrier;
    }

    @Override
    public void run() {
        System.out.println(id+"运动员开跑了");
        try {
            Thread.sleep(2000);
            System.out.println(id+"运动员到达终点");
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}