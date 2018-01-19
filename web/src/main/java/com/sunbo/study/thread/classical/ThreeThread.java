package com.sunbo.study.thread.classical;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author sunboyan
 * Date 2018/1/19
 * Description 编写一个程序，启动三个线程，三个线程的ID分别是A，B，C；
 * 每个线程将自己的ID值在屏幕上打印5遍，打印顺序是ABCABC...
 */
public class ThreeThread {

    public static void main(String[] args){
        JobThread3 my = new JobThread3();

        //线程1
        ThreadFactory threadFactory1 = new ThreadFactoryBuilder()
                .setNameFormat("线程1").build();
        ExecutorService threadPool1 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory1, new ThreadPoolExecutor.AbortPolicy());
        threadPool1.execute(() ->{
            // TODO Auto-generated method stub
            for(int i = 0; i < 5; i++){
                my.printA();
            }
        });
        threadPool1.shutdown();
        //线程2
        ThreadFactory threadFactory2 = new ThreadFactoryBuilder()
                .setNameFormat("线程2").build();
        ExecutorService threadPool2 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory2, new ThreadPoolExecutor.AbortPolicy());
        threadPool2.execute(() ->{
            // TODO Auto-generated method stub
            for(int i = 0; i < 5; i++){
                my.printB();
            }
        });
        threadPool2.shutdown();

        //线程3
        ThreadFactory threadFactory3 = new ThreadFactoryBuilder()
                .setNameFormat("线程3").build();
        ExecutorService threadPool3 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory3, new ThreadPoolExecutor.AbortPolicy());
        threadPool3.execute(() ->{
            // TODO Auto-generated method stub
            for(int i = 0; i < 5; i++){
                my.printC();
            }
        });
        threadPool3.shutdown();
    }
}

class JobThread3{

    private  int flag = 1;

    public synchronized void printA(){
        while(flag != 1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.print("A");
        flag = 2;
        this.notifyAll();
    }
    public synchronized void printB(){
        while(flag != 2){
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.print("B");
        flag = 3;
        this.notifyAll();
    }
    public synchronized void printC(){
        while(flag != 3){
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("C");
        flag = 1;
        this.notifyAll();
    }
}
