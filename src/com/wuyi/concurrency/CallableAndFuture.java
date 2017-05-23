package com.wuyi.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by LENOVO on 2017/5/22.
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        Callable<Integer> callable=new Callable <Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        };
        FutureTask<Integer> future=new FutureTask <Integer>(callable);

        new Thread(future).start();
        try {
//            Thread.sleep(500);
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
