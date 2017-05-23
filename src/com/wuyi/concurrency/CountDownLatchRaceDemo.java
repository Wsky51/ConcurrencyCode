package com.wuyi.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by LENOVO on 2017/5/23.
 */
//用与理解和栅栏的区别，5人跑步同时开始起跑，最后一个人跑完后才结束
public class CountDownLatchRaceDemo {
    //下面是闭锁CountDownLatch的测试代码
    public static void main(String[] args) {
        CountDownLatch begin=new CountDownLatch(1);
        CountDownLatch end=new CountDownLatch(5);
//        begin.countDown();
        for (int i=0;i<5;i++){
            new Thread(new Runner(i,begin,end)).start();
        }
        long be = System.nanoTime();
        System.out.println("judge:3,2,1,Race begin!!! ");
        begin.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            long endtime=System.nanoTime();
            System.out.println("judge:所有人已经到达");
            System.out.println("从开始比赛到最后一名到达花费时间"+(endtime-be));
        }
    }
}

class Runner implements Runnable{
    private final int id;
    private final CountDownLatch begin;
    private final CountDownLatch end;
    public Runner(final int id,final CountDownLatch begin,final CountDownLatch end){
        this.id=id;
        this.begin=begin;
        this.end=end;
    }

    @Override
    public void run() {
        try {
            begin.await();
            System.out.println(id+"号运动员开跑了");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(id+"号运动员跑过终点");
            end.countDown();
        }
    }
}
