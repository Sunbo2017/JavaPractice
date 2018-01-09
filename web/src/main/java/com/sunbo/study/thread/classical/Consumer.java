package com.sunbo.study.thread.classical;

/**
 * @author sunboyan
 * Date 2018/1/9
 * Description 生产者消费者：消费者
 */
public class Consumer implements Runnable{
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
