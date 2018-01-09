package com.sunbo.study.thread.classical;

/**
 * @author sunboyan
 *         Date 2018/1/9
 *         Description
 */
public class ProducerConsumerTest {

    public static void main(String args[]) {
        Resource resource = new Resource();
        new Thread(new Producer(resource)).start();//生产者线程
        new Thread(new Consumer(resource)).start();//消费者线程

    }

}
