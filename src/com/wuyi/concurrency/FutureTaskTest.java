package com.wuyi.concurrency;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by LENOVO on 2017/5/23.
 */
public class FutureTaskTest {
    public static void main(String[] args) {
        ExecutorService es= Executors.newCachedThreadPool();
        Future <Integer> future = es.submit(new Callable <Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        try {
            Integer integer = future.get();

            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
