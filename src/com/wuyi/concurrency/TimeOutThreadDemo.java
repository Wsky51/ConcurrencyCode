package com.wuyi.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by LENOVO on 2017/5/23.
 */

/**
 *用来测试FutureTask.get(long,TimeUnit)在指定时间失效的方法
 *
 * <p>这个测试类进行模拟设计网络界面中加载广告的过程</p>
 *
 * @author WuYi
 * @Since 17年5月23日
 */
public class TimeOutThreadDemo {
    /**
     * 创建一个线程池，固定大小为5，默认的加载时间为4s，超过时间广告将会以默认的广告界面加载
     */
    private static ExecutorService es= Executors.newFixedThreadPool(5);
    private final static long timeout =4;

    /**
     * 加载广告，如果传入相应的界面后加载广告的时间大于默认的时间{@code 4s}那么该方法将自动加载一个默认的广告
     *
     * @param pageTitle 页面的名称
     * @throws InterruptedException 如果{@code futureTask被迫中断}
     * @throws ExecutionException 如果出现执行时的异常
     * @throws TimeoutException 如果超时
     *
     */
    public static void loadAdByPage(final String pageTitle){
        String page;
        FutureTask<String> futureTask=new FutureTask <String>(new Callable <String>() {
            @Override
            public String call() throws Exception {
                System.out.println("开始加载广告信息");
                int loadtime=new Random().nextInt(5)+1;
                TimeUnit.SECONDS.sleep(loadtime);
                System.out.println("正常加载广告完毕，耗时"+loadtime);
                return pageTitle;
            }
        });
        es.submit(futureTask);
        //在预定时间4s内进行等待
        System.out.println("预期任务执行完时间"+timeout+"s");
        try {
            page=futureTask.get(timeout,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            page="出现中断，将加载默认广告";
        } catch (ExecutionException e) {
            page="任务执行异常，将加载默认广告";
        } catch (TimeoutException e) {
            page="任务执行超时了，显示默认的广告页面";
            futureTask.cancel(true);
        }
        System.out.println("成功加载广告页面:"+page);
    }

    public static void main(String[] args) {
        List<String> titleList=new ArrayList <String>();
        titleList.add("体育");
        titleList.add("娱乐");
        titleList.add("国际");
        titleList.add("国内");
        titleList.add("社会");
        for (String pageTitle:titleList){
            loadAdByPage(pageTitle);
        }
        es.shutdown();
        while (true){
            if (es.isTerminated()){
                System.out.println();
                System.out.println("所有任务执行完毕,关闭线程池");
                break;
            }
        }
    }
}
