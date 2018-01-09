package com.sunbo.study.thread.wait;

/**
 * @author sunboyan
 * Date 2018/1/9
 * Description wait用法
 */
public class WaitThread implements Runnable{
    public String name;
    public Object prev;
    public Object self;

    public WaitThread(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;

                    self.notify();
                }try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
