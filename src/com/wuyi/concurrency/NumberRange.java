package com.wuyi.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LENOVO on 2017/5/19.
 */
public class NumberRange {
    //不变性条件
    private final AtomicInteger lower=new AtomicInteger(0);
    private final AtomicInteger upper=new AtomicInteger(0);

    //这是不安全的“先检查后执行”
    public void setLower(int i){
        if(i>upper.get()){
            throw new IllegalArgumentException("can't set lower to"+i+">upper");
        }
        lower.set(i);
    }
    //也是不安全的“先检查后执行”
    public void setUpper(int i){
        if(i<lower.get()){
            throw new IllegalArgumentException("can't set upper to "+i+"<lower");
        }
        upper.set(i);
    }
    public boolean isInRange(int i){
        return (i>=lower.get()&&i<=upper.get());
    }

    public static void main(String[] args) {
        NumberRange demo=new NumberRange();
        demo.setUpper(30);
        demo.setLower(1);
//        System.out.println(demo.isInRange(25));
    }
}
