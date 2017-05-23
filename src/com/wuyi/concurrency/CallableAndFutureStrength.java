package com.wuyi.concurrency;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by LENOVO on 2017/5/22.
 */
//多个返回值测试类
public class CallableAndFutureStrength {
    public static void main(String[] args) {
            ExecutorService es= Executors.newSingleThreadExecutor();
            CompletionService cs=new ExecutorCompletionService(es);
            for (int i=1;i<5;i++){
                final int taskID=i;
            cs.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    return new Random().nextInt();
                }
            });
        }
        for (int i=1;i<5;i++){
            try {
                Future take = cs.take();
                System.out.println(take.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
