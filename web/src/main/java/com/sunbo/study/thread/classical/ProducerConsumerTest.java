package com.sunbo.study.thread.classical;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author sunboyan
 *         Date 2018/1/9
 *         Description
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
