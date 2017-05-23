package com.wuyi.concurrency;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by LENOVO on 2017/5/21.
 */
public class CellularAutoMata {
    private final CyclicBarrier barrier=new CyclicBarrier(4, new Runnable() {
        @Override
        public void run() {

        }
    });
    public static void main(String[] args) {

        System.out.println("可供运行的处理器"+Runtime.getRuntime().availableProcessors());
    }
}
