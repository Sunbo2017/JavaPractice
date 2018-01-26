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

        //线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("线程1").build();
        ExecutorService threadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        threadPool.execute(() ->{
            // TODO Auto-generated method stub
            for(int i = 0; i < 5; i++){
                my.printA();
                my.printB();
                my.printC();
            }
        });
        threadPool.shutdown();
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
