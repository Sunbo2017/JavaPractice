package com.sunbo.study.thread.classical;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author sunboyan
 *         Date 2018/1/9
 *         Description 生产者消费者
 */
public class ProducerConsumerTest {

    public static void main(String args[]) {
        Resource resource = new Resource();
        //生产者线程
        ThreadFactory producerThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("生产者线程").build();
        ExecutorService producerThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), producerThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        producerThreadPool.execute(new Producer(resource));
        producerThreadPool.shutdown();
        //消费者线程
        ThreadFactory consumerThreadFactory1 = new ThreadFactoryBuilder()
                .setNameFormat("消费者线程").build();
        ExecutorService consumerThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), consumerThreadFactory1, new ThreadPoolExecutor.AbortPolicy());
        consumerThreadPool.execute(new Consumer(resource));
        consumerThreadPool.shutdown();

    }

}

/**
 * 产品资源
 */
class Resource {
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

/**
 * 消费者
 */
class Consumer implements Runnable{
    private int count = 0;
    private Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (count <= 100) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.destroy();
            count++;
        }
    }
}

/**
 * 生产者
 */
class Producer implements Runnable{
    private int count = 0;
    private Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (count <= 100) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.create();
            count++;
        }

    }
}
