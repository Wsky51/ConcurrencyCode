package com.wuyi.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by LENOVO on 2017/5/18.
 */
//NotThreadSafe
public class LazyInitRace {
    private static ExpensiveObject instance;
    public static ExpensiveObject getInstance() {
        if (instance==null){
            instance=new ExpensiveObject();
        }
        return instance;
    }
    public static void main(String[] args) {
        ExecutorService es=Executors.newCachedThreadPool();
        for (int i=0;i<100;i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(LazyInitRace.getInstance().hashCode());
                }
            });
        }
        es.shutdown();
    }
}

class ExpensiveObject{

}
