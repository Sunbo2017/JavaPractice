package com.sunbo.study.thread.classical;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author sunboyan
 * Date 2018/1/19
 * Description 子线程循环10次，主线程循环20次，然后子线程循环10次，主线程循环20次，这样循环5次；
 * 思路：使用synchornized+wait，notify方法；
 * 难点在于主线程的启动不需要start方法，
 * 因为程序的入口是main方法，
 * 在执行这个程序的时候，
 * 主线程已经启动了。
 */
public class SubMain {
    public static void main(String args[]){
        JobThread2 m = new JobThread2();

        //子线程
        ThreadFactory threadFactory1 = new ThreadFactoryBuilder()
                .setNameFormat("线程1").build();
        ExecutorService threadPool1 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory1, new ThreadPoolExecutor.AbortPolicy());
        threadPool1.execute(() ->{
            // TODO Auto-generated method stub
            for(int i = 0; i < 5; i++){
                m.subThread();
            }
        });
        threadPool1.shutdown();

        //主线程
        for(int i = 0; i < 5; i++){
            m.mainThread();
        }
    }


}

class JobThread2{
    /**flag是true时执行sub方法，flag是false时执行mainThread方法*/
    private boolean flag = true;
    public synchronized void subThread(){
        //如果flag==false，说明另一个线程拥有该对象的锁，调用sub的方法被阻塞，直到另一个线程释放锁，唤醒该线程。
        while(flag == false){
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for(int i = 0 ; i<10; i++){
            System.out.print("子线程；");
        }
        System.out.println();
        flag = false;
        this.notify();
    }

    public synchronized void mainThread(){
        while(flag == true){
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for(int i = 0 ; i < 20; i++){
            System.out.print("主线程；");
        }
        System.out.println();
        flag = true;
        this.notify();
    }

}
