package com.wuyi.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by LENOVO on 2017/5/18.
 */
public class Test {
    List<String> list=new ArrayList <>();
    public  synchronized void Method1(){
        System.out.println("这是同步方法");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void Method2(){
        synchronized (Test.this){
            System.out.println("这是同步代码块");
        }
    }


    public static void main(String[] args) {
        final Test test=new Test();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                test.Method1();
            }
        });
        t1.start();

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                test.Method2();
            }
        });
        t2.start();
    }
}
