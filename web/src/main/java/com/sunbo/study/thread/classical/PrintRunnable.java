package com.sunbo.study.thread.classical;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author sunboyan
 * Date 2018/1/10
 * Description 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10,然后是线程3打印11,12,13,14,15.
 * 接着再由线程1打印16,17,18,19,20....以此类推, 直到打印到75.
 */
public class PrintRunnable implements Runnable{

    private static volatile int printNum = 0;
    private int threadId;

    public PrintRunnable(int threadId){
        this.threadId = threadId;
    }
    @Override
    public void run() {
        while(printNum < 75){
            synchronized (Print.class){
                if (printNum/5%3 + 1 == threadId){
                    for (int i = 0; i < 5; i++) {
                        System.out.println("线程"+threadId+":"+(++printNum));
                    }
                    Print.class.notifyAll();
                }else {
                    try {
                        Print.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}

class Print {

    public static void main(String[] args) {
        //线程1
        ThreadFactory threadFactory1 = new ThreadFactoryBuilder()
                .setNameFormat("线程1").build();
        ExecutorService threadPool1 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory1, new ThreadPoolExecutor.AbortPolicy());
        threadPool1.execute(new PrintRunnable(1));
        threadPool1.shutdown();
        //线程2
        ThreadFactory threadFactory2 = new ThreadFactoryBuilder()
                .setNameFormat("线程2").build();
        ExecutorService threadPool2 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory2, new ThreadPoolExecutor.AbortPolicy());
        threadPool2.execute(new PrintRunnable(2));
        threadPool2.shutdown();
        //线程3
        ThreadFactory threadFactory3 = new ThreadFactoryBuilder()
                .setNameFormat("线程3").build();
        ExecutorService threadPool3 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory3, new ThreadPoolExecutor.AbortPolicy());
        threadPool3.execute(new PrintRunnable(3));
        threadPool3.shutdown();
    }
}
