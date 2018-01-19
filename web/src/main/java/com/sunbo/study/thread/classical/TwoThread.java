package com.sunbo.study.thread.classical;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author sunboyan
 * Date 2018/1/17
 * Description 写两个线程，一个线程打印1~52，另一个线程打印A~Z，打印顺序是12A34B...5152Z
 * 思路分析: 一个线程打印数字，每次打印两个数字，26次打印完，另一个线程打印字母，也是26次打印完；
 * 可以创建两个方法，一个方法打印数字，一个打印字母；
 * 还有创建一个全局变量用来控制具体执行的是哪个线程；
 * 每个方法都被执行26次
 */
public class TwoThread {
    public static void main(String args[]){
        JobThread my = new JobThread();
        //线程1
        ThreadFactory threadFactory1 = new ThreadFactoryBuilder()
                .setNameFormat("线程1").build();
        ExecutorService threadPool1 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory1, new ThreadPoolExecutor.AbortPolicy());
        threadPool1.execute(() ->{
                // TODO Auto-generated method stub
                for(int i = 0; i < 26; i++){
                    my.printNum();
                }
        });
        threadPool1.shutdown();
        //线程2
        ThreadFactory threadFactory2 = new ThreadFactoryBuilder()
                .setNameFormat("线程2").build();
        ExecutorService threadPool2 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory2, new ThreadPoolExecutor.AbortPolicy());
        threadPool2.execute(() ->{
            // TODO Auto-generated method stub
            for(int i = 0; i < 26; i++){
                my.printA();
            }
        });
        threadPool2.shutdown();
    }
}

class JobThread{
    private static boolean flag = true;
    public int count = 1;
    public synchronized void printNum(){
        while (flag == false){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.print((2*count-1));
        System.out.print(2*count);

        flag = false;
        this.notify();
    }

    public synchronized void printA(){
        while (flag == true){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.print((char)(count+'A'-1));
        count++;
        flag = true;
        this.notify();
    }
}