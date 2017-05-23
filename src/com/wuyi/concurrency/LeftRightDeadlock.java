package com.wuyi.concurrency;

/**
 * Created by LENOVO on 2017/5/21.
 */
public class LeftRightDeadlock implements Runnable {
    private final Object left = new Object();
    private final Object right = new Object();
    public void doSomething() {
        System.out.println("做些事");
    }

    public void doSomethingelse() {
        System.out.println("做些其他事");
    }

    public void leftRight() {
        synchronized (left) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (right) {
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (left) {
                doSomethingelse();
            }
        }
    }

    @Override
    public void run() {
        leftRight();
    }

    public static void main(String[] args) {
        final LeftRightDeadlock demo = new LeftRightDeadlock();
        final LeftRightDeadlock demo2 = new LeftRightDeadlock();
//        ExecutorService es = Executors.newCachedThreadPool();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                demo.leftRight();
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                demo.rightLeft();
            }
        });
            t1.start();
            t2.start();

//        es.shutdown();
    }
}
