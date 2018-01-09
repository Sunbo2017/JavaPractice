package com.sunbo.study.thread.classical;

/**
 * @author sunboyan
 * Date 2018/1/9
 * Description 生产者消费者：生产者
 */
public class Producer implements Runnable{
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
