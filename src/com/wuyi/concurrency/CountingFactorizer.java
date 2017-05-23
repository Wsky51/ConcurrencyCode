package com.wuyi.concurrency;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by LENOVO on 2017/5/18.
 */
//ThreadSafe
public class CountingFactorizer{
    private final AtomicLong count=new AtomicLong(0);
    public static void main(String[] args) {
        TimerTask t=new TimerTask() {
            @Override
            public void run() {
                System.out.println("你好");
            }
        };
        t.run();
    }
    public long getCount() {
        return count.get();
    }
    public void swevice(){

    }
}
