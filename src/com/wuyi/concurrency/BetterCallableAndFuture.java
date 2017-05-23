package com.wuyi.concurrency;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by LENOVO on 2017/5/22.
 */
public class BetterCallableAndFuture {
    public static void main(String[] args) {
        ExecutorService es= Executors.newSingleThreadExecutor();
        Future <Integer> future = es.submit(new Callable <Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        try {
            Thread.sleep(500);//可能会去做一些其他的事情......
            Integer integer = null;
            integer = future.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
