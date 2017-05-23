package com.wuyi.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by LENOVO on 2017/5/21.
 */
public class OutOfTIME {
    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.schedule(new ThrowTask(),1);
        try {
            TimeUnit.SECONDS.sleep(1);
            timer.schedule(new ThrowTask(),1);
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class ThrowTask extends TimerTask{
        public void run(){
            throw new RuntimeException();
        }
    }
}
