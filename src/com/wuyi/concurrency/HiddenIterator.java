package com.wuyi.concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LENOVO on 2017/5/20.
 */

public class HiddenIterator {
    private final Set<Integer> set1=new HashSet <Integer>();
    private final Set<Integer> set= Collections.synchronizedSet(set1);

    public synchronized void add(Integer i){
        set.add(i);
    }
    public synchronized void remove(Integer i){
        set.remove(i);
    }

    public void addTenThings(){
        Random r=new Random();
        for (int i=0;i<10;i++){
            add(r.nextInt());
//            System.out.println("循环体里面");
        }
        System.out.println("输出结果是"+set);
//        System.out.println("-----外部------");
    }


    public static void main(String[] args) {
        final HiddenIterator demo=new HiddenIterator();
        ExecutorService es=Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    demo.addTenThings();
                }
            });
        }
        es.shutdown();
    }
}
