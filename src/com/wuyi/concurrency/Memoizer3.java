package com.wuyi.concurrency;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by LENOVO on 2017/5/21.
 */
//基于FutureTask的Memoizing封装器
interface Computable<A,V>{
        V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction implements Computable<String,BigInteger>{
    @Override
    public BigInteger compute(String arg){
    //假设经过了长时间的计算
    return new BigInteger(arg);
    }
}

public class Memoizer3<A,V> implements Computable<A,V>{
    private final Map<A,Future<V>> cache=new ConcurrentHashMap <A,Future<V>>();
    private final Computable<A,V> c;
    public Memoizer3(Computable<A,V> c){this.c=c;}
    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f=cache.get(arg);
        if (f==null){
            Callable<V> eval=new Callable <V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft=new FutureTask <V>(eval);
            f=ft;
            cache.put(arg,ft);
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
