package com.sunbo.study.thread.classical;

/**
 * @author sunboyan
 * Date 2018/1/9
 * Description 生产者消费者：资源
 */
public class Resource {
    /**资源序号*/
    private int number = 0;
    /**资源标记*/
    private boolean flag = false;

    /**
     * 生产资源
     */
    public synchronized void create() {
        //先判断标记是否已经生产了，如果已经生产，等待消费；
        if (flag) {
            try {
                //让生产线程等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //生产一个
        number++;
        System.out.println(Thread.currentThread().getName() + ":生产者--------" + number);
        //将资源标记为已经生产
        flag = true;
        //唤醒在等待操作资源的线程（队列）
        notify();
    }

    /**
     * 消费资源
     */
    public synchronized void destroy() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":消费者********" + number);
        flag = false;
        notify();
    }
}
