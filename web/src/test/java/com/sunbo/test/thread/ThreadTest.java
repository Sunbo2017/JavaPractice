package com.sunbo.test.thread;

import com.sunbo.study.thread.wait.WaitThread;
import org.junit.Test;

/**
 * @author sunboyan
 *         Date 2018/1/9
 *         Description
 */
public class ThreadTest {

    @Test
    public void testWait() throws Exception{
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        WaitThread pa = new WaitThread("A", c, a);
        WaitThread pb = new WaitThread("B", a, b);
        WaitThread pc = new WaitThread("C", b, c);

        new Thread(pa).start();
        Thread.sleep(100);  //确保按顺序A、B、C执行
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }

}
