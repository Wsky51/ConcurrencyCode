package com.wuyi.concurrency;

/**
 * Created by LENOVO on 2017/5/20.
 */

public class InterfaceDemo {

}

abstract class E{
    public abstract void methodE();
    public abstract void methodE2();
    public void methodE3(){
        System.out.println("你好");
    }
}

interface A{
    public void methodA();
    public void methodA1();
    public void methodA2();
}

interface B{
    public void methodB();
}

interface C{
    public void methodC();
}

interface D{
    public void methodD();
}
