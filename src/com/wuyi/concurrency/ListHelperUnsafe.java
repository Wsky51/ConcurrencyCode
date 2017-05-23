package com.wuyi.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by LENOVO on 2017/5/19.
 */
//unsafe
public class ListHelperUnsafe<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList <E>());

    public synchronized boolean putIfAbsent(E x){
        boolean absent=!list.contains(x);
        if (absent){
            list.add(x);
        }
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return absent;
    }
    public synchronized boolean putIfAbsent2(E x){
        synchronized (this){
            System.out.println("得到锁了");
        }
        return false;
    }

    public static void main(String[] args) {
        ListHelperUnsafe demo=new ListHelperUnsafe();

    }
}
