package com.wuyi.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LENOVO on 2017/5/18.
 */

//unSafe
public class MutableInteger implements Runnable{
    private int value;

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        setValue(++value);
        System.out.println(Thread.currentThread().getName()+"值： "+getValue());
    }

    public static void main(String[] args) {
        MutableInteger mutableInteger = new MutableInteger();
        ExecutorService es= Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            es.execute(mutableInteger);
        }
    }
}
