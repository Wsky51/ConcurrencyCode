package com.wuyi.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LENOVO on 2017/5/19.
 */
public class AtomicIntegerDeno implements Runnable{
    private volatile int i=0;
    private AtomicInteger i1=new AtomicInteger(0);

    @Override
    public void run() {
        observer();

    }
    public  void observer(){
        System.out.println("i的值是"+(++i));
//        i1.incrementAndGet();
//        System.out.println("原子变量的值是"+i1);
    }



    public static void main(String[] args) {
        ExecutorService es= Executors.newCachedThreadPool();
        AtomicIntegerDeno demo=new AtomicIntegerDeno();
        for(int i=0;i<1000;i++){
            es.execute(demo);
        }
    }
}
