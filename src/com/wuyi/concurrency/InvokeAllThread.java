package com.wuyi.concurrency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by LENOVO on 2017/5/23.
 */
public class InvokeAllThread {
//固定线程池的大小
    static ExecutorService es= Executors.newFixedThreadPool(5);
    private class QuoteTask implements Callable<BigDecimal>{
        public final double price;
        public final int num;
        public QuoteTask(double price,int num){
            this.price=price;
            this.num=num;
        }

        @Override
        public BigDecimal call() throws Exception {
            Random random=new Random();
            int sleeptime = random.nextInt(10) + 1;
            TimeUnit.SECONDS.sleep(sleeptime);
            BigDecimal d=BigDecimal.valueOf(price*num).setScale(2,BigDecimal.ROUND_HALF_UP);
            System.out.println("耗时"+sleeptime+"s,单价是："+price+",人数是："+num+",总额是:"+d);
            return d;
        }
    }
    /**
     * 在规定的时间内获得所有旅游团的报价
     *
     */
    public void getRankedTraQuote() throws InterruptedException{
        List<QuoteTask> tasks=new ArrayList <>();
        //模拟计算10个旅游团的报价
        for (int i=1;i<10;i++){
            tasks.add(new QuoteTask(200,i));
        }
        //使用InvokeAll方法批量限时调用所有任务，预计10秒内所有任务执行完毕，没有执行完毕的自动取消

        List<Future<BigDecimal>> futures=es.invokeAll(tasks,15,TimeUnit.SECONDS);
        //报价的List集合
        List<BigDecimal> totalPriceList=new ArrayList <>();
        Iterator <QuoteTask> iterator = tasks.iterator();
        for (Future<BigDecimal> future:futures){
            QuoteTask task = iterator.next();
            try {
                totalPriceList.add(future.get());
            } catch (ExecutionException e) {
                System.out.println("失败");
                e.printStackTrace();
            }
        }

        for (BigDecimal bigDecimal:totalPriceList){
            System.out.println(bigDecimal);
        }
        es.shutdown();
    }

    public static void main(String[] args) {
        InvokeAllThread invokeAllThread=new InvokeAllThread();
        try {
            invokeAllThread.getRankedTraQuote();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
